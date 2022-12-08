package bilibili.relation;
import java.util.*;

public class TopRelation {
	
	public void relation() {
		int x,y;
		ServerReceiver server = new ServerReceiver("server");
		OperationCommand op1 = new Operation1(server,"关注up主");
		OperationCommand op2 = new Operation2(server,"取关up主");
		OperationCommand op3 = new Operation3(server,"拉黑up主");
		OperationCommand op4 = new Operation4(server,"加入应援团");
		OperationCommand op5 = new Operation5(server,"退出应援团");
		OperationCommand op6 = new Operation6(server,"创建应援团");
	
		while(true) {
			UserInvoker user = new UserInvoker();
			System.out.print("请选择要进行的操作:\n1.关注up主\n2.取关up主\n3.拉黑up主\n4.加入应援团\n5.退出应援团\n6.创建应援团\n7.退出\n");
			Scanner in = new Scanner(System.in);
			x = in.nextInt();
			switch(x) {
				case 1:
					user.addOperation(op1);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op1);
					break;
				case 2:
					user.addOperation(op2);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op2);
					break;
				case 3:
					user.addOperation(op3);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op3);
					break;
				case 4:
					user.addOperation(op4);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op4);
					break;
				case 5:
					user.addOperation(op5);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op5);
					break;
				case 6:
					user.addOperation(op6);
					System.out.print("是否确认该操作？\n1.是\n2.否\n");
					y = in.nextInt();
					if(y == 2)
						user.delOperation(op6);
					break;
				case 7:
					System.out.println("退出关系系统服务!");
					return;
				default:
					System.out.println("非法输入");
					break;
			}
			user.notifyServer();
			user.notifyNotServer();
		}
	}
}
