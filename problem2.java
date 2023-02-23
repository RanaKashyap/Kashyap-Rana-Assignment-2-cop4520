import java.util.Random;

class VaseRoom{
    private boolean roomOccupied = false;
    
    public void lookVase(){
            this.roomOccupied = true;
            try {
                // some time guest take to look at vase
                Thread.sleep(1);
            } catch (Exception e) {
                // TODO: handle exception
            }
            this.roomOccupied = false;
    }

    public boolean getRoomAvailability(){return this.roomOccupied;}
}

public class problem2 {
    public static void main(String[] args) throws Exception
    {
        final int N = (args.length > 0) ? Integer.valueOf(args[0]) : 100;
        VaseRoom vase = new VaseRoom();
        int count = 0;

        Thread[] guestList = new Thread[N];
        

        for(int i = 0; i < N; i++) {
			guestList[i] = new Thread(new Runnable() {
                    private boolean visitedVaseRoom = false;
            
                    @Override
                    public void run() 
                    {
                        this.visitedVaseRoom = true;
                        vase.lookVase();
                    }
                }
            );
		}

        long startTime = System.nanoTime();
        while(count != N){
            int randomNumber = (int) (Math.random()*(N));
            guestList[randomNumber].run();
            try {
                guestList[randomNumber].join();
            } catch (Exception e) {}
            count++;
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        long timeInMs = (totalTime/1000000);
        

        System.out.println("value of N: "+N+ "\ntime: "+ timeInMs + " ms");
    }
}
