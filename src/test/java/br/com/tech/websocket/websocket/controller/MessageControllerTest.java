package br.com.tech.websocket.websocket.controller;

import br.com.tech.websocket.app.WebsocketApplication;
import br.com.tech.websocket.app.data.Message;
import br.com.tech.websocket.websocket.configuration.WebSocketConfigTest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {WebsocketApplication.class, WebSocketConfigTest.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {


    private static final Logger LOG = LoggerFactory.getLogger(MessageControllerTest.class);

    @LocalServerPort
    private int port;

    private WebSocketStompClient stompClient;
    private BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

    @BeforeEach
    void setUp(TestInfo testInfo) {
        LOG.debug("🔧 Configurando o cliente WebSocket para o teste '" + testInfo.getDisplayName() + "'");

        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @BeforeAll
    static void beforeAll() {
        LOG.info("🔧 Inicializando os testes WebSocket...");
    }

    @AfterAll
    static void afterAll() {
        LOG.info("✅ Testes WebSocket finalizados.");
    }

    @Test
    @Order(1)
    @DisplayName("🔄 Teste de troca de mensagens via WebSocket")
    void testWebSocketMessageExchange() throws Exception {
        String wsUrl = "ws://localhost:" + port + "/wb-guide-websocket";

        LOG.info("🌍 Conectando ao WebSocket: " + wsUrl);
        StompSession session = stompClient.connectAsync(wsUrl, new StompSessionHandlerAdapter() {}).get(3, TimeUnit.SECONDS);

        session.subscribe("/topic/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                LOG.info("📥 Mensagem recebida do WebSocket: " + payload);
                Message message = (Message) payload;
                blockingQueue.offer(message.getContent());
            }
        });

        LOG.info("✅ Cliente WebSocket inscrito no tópico '/topic/messages'");

        String messageToSend = "Hello WebSocket!";

        LOG.info("📤 Enviando mensagem: " + messageToSend);
        session.send("/app/socket", messageToSend);

        String receivedMessage = blockingQueue.take();

        LOG.info("✅ Mensagem final recebida: " + receivedMessage);

        assertNotNull(receivedMessage);
        assertEquals(messageToSend, receivedMessage);
    }
}
