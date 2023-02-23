import java.util.Random;

class Maze{
    private boolean cake = true;
    
    public void visitMaze(){
        this.cake = true;
    }

    public boolean getCake(){return this.cake;}
    public void setCake(boolean cake){this.cake = cake;}
}

public class problem1 {
    public static void main(String[] args) throws Exception
    {
        final int N = (args.length > 0) ? Integer.valueOf(args[0]) : 100;
        int loop = 0;
        Maze maze1 = new Maze();
        int count = 1;
        int leader = N-1;

        Thread[] guestList = new Thread[N-1];
        

        for(int i = 0; i < N-1; i++) {
			guestList[i] = new Thread(new Runnable() {
                    private boolean visitedMaze = false;
            
                    @Override
                    public void run() 
                    {
                        if((this.visitedMaze == false) && (maze1.getCake() == false))
                        {
                            this.visitedMaze = true;
                            maze1.visitMaze();
                        }
                    }
                }
            );
		}

        long startTime = System.nanoTime();
        while(count != N){
            int randomNumber = (int) (Math.random()*(N));
            if(randomNumber == leader)
            {
                if(maze1.getCake() == true)
                {
                    count++;
                    maze1.setCake(false);
                }
                
            }else{
                guestList[randomNumber].run();
                try {
                    guestList[randomNumber].join();
                } catch (Exception e) {}
            }
            loop++;
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        long timeInMs = (totalTime/1000000);
        

        System.out.println("value of N: "+N+ "\ntime: "+ timeInMs + " ms");
    }
}
