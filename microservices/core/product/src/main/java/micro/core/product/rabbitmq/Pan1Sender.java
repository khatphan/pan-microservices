/*
package micro.core.product.rabbitmq;


public class Pan1Sender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 10000, initialDelay = 500)
	public void send(){
		String message = "Hello Rabbitmq!!!";
		this.rabbitTemplate.convertAndSend(queue.getName(), message);

		System.out.println(" [x] Sent '" + message + "'");
	}
}
*/
