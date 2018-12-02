package exception;

public class IdOverlapException extends Exception{
	
	String id;
	
	public IdOverlapException()	 { }
	
		public IdOverlapException(String id) {
			super(id);
			this.id = id;
			
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String getMessage() {
			return super.getMessage()+"중복된 ID입니다.초기화면으로 돌아갑니다.";
		};
		
	}

