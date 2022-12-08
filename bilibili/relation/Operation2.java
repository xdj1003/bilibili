package bilibili.relation;

public class Operation2 extends OperationCommand {
	
	public Operation2(ServerReceiver serverReceiver,String operationName) {
		super(serverReceiver,operationName);
	}
	
	public void executeCommand(ServerReceiver serverReceiver,String operationName) {
		serverReceiver.operating(operationName);
	};
	public void cancelCommand(ServerReceiver serverReceiver,String operationName) {
		serverReceiver.notoperating(operationName);
	};
}