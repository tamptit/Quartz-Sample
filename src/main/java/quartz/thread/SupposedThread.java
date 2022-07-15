package quartz.thread;

public class SupposedThread  implements Runnable{

    private final Long current = System.currentTimeMillis();
    private Boolean newTrigger = false;
    private Integer count = 0;

    @Override
    public void run() {
        System.out.println("running..." + count++);
//        long periodTime = System.currentTimeMillis() - current;
//        if ( !newTrigger && periodTime > 5000 ){
//            newTrigger = true;
//            System.out.println("new Trigger starting...");
//        }else{
//            System.out.println("running...");
//        }
    }

    public Long getCurrent() {
        return current;
    }

    public Boolean getNewTrigger() {
        return newTrigger;
    }

    public void setNewTrigger(Boolean newTrigger) {
        this.newTrigger = newTrigger;
    }
}
