import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 * Date: 23/11/13
 * Time: 5:32 PM
 * A group of N high school students wants to play a basketball game. To divide themselves into two teams they first rank all the players in the following way:
 * 
 * Players with a higher shot percentage are rated higher than players with a lower shot percentage.
 * If two players have the same shot percentage, the taller player is rated higher.
 * Luckily there are no two players with both the same shot percentage and height so they are able to order themselves in an unambiguous way. Based on that ordering each player is assigned a draft number from the range [1..N], where the highest-rated player gets the number 1, the second highest-rated gets the number 2, and so on. Now the first team contains all the players with the odd draft numbers and the second team all the players with the even draft numbers.
 * 
 * Each team can only have P players playing at a time, so to ensure that everyone gets similar time on the court both teams will rotate their players according to the following algorithm:
 * 
 * Each team starts the game with the P players who have the lowest draft numbers.
 * If there are more than P players on a team after each minute of the game the player with the highest total time played leaves the playing field. Ties are broken by the player with the higher draft number leaving first.
 * To replace her the player on the bench with the lowest total time played joins the game. Ties are broken by the player with the lower draft number entering first.
 * The game has been going on for M minutes now. Your task is to print out the names of all the players currently on the field, (that is after M rotations).
 * 
 * Input
 * The first line of the input consists of a single number T, the number of test cases.
 * 
 * Each test case starts with a line containing three space separated integers N M P
 * 
 * The subsequent N lines are in the format "<name> <shot_percentage> <height>". See the example for clarification.
 * 
 * Constraints
 * 1 ≤ T ≤ 50
 * 2 * P ≤ N ≤ 30
 * 1 ≤ M ≤ 120
 * 1 ≤ P ≤ 5
 * Each name starts with an uppercase English letter, followed by 0 to 20 lowercase English letters. There can be players sharing the same name. Each shot percentage is an integer from the range [0..100]. Each height is an integer from the range [100..240]


 */

class Player{
    private String name;
    private int height;
    private int shotpercentage;
    private int draftnumber;
    private int timespentoncourt;

    public int getTimespentoncourt() {
        return timespentoncourt;
    }

    public void setTimespentoncourt(int timespentoncourt) {
        this.timespentoncourt = timespentoncourt;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getShotpercentage() {
        return shotpercentage;
    }

    public void setShotpercentage(int shotpercentage) {
        this.shotpercentage = shotpercentage;
    }

    public int getDraftnumber() {
        return draftnumber;
    }

    public void setDraftnumber(int draftnumber) {
        this.draftnumber = draftnumber;
    }

}
public class BasketBallGame {

    ArrayList<Player> team1;
    ArrayList<Player> team2;
    ArrayList<Player> allplayers;
    ArrayList<Player> team1onthecourt;
    ArrayList<Player> team2onthecourt;

    public static void main(String args[]){
        BasketBallGame bbg=new BasketBallGame();
        bbg.readFile(args[0]);
    }

    public void readFile(String filename){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                //StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                int testCases=Integer.parseInt(line);
                int currentTestCase=1;
                while(currentTestCase<=testCases){
                    getPlayersAfterMRotations(br);
                    System.out.println("");
                    printplayersoncourt(currentTestCase);
                    currentTestCase+=1;
                }
                br.close();
            }

            catch(IOException e){

            }
        }
        catch(FileNotFoundException e){

        }

    }

    public void getPlayersAfterMRotations(BufferedReader br){
        allplayers=new ArrayList<Player>();
        team1=new ArrayList<Player>();
        team2=new ArrayList<Player>();
        team1onthecourt=new ArrayList<Player>();
        team2onthecourt=new ArrayList<Player>();
        try{
            String line = br.readLine();
            String[] params=line.split(" ");
            int N=Integer.parseInt(params[0]);
            int M=Integer.parseInt(params[1]);
            int P=Integer.parseInt(params[2]);

            int currentPlayer=1;
            while(currentPlayer<=N){
                line=br.readLine();
                params=line.split(" ");
                Player p=new Player();
                p.setName(params[0]);
                p.setShotpercentage(Integer.parseInt(params[1]));
                p.setHeight(Integer.parseInt(params[2]));
                p.setTimespentoncourt(0);
                allplayers.add(p);
                currentPlayer+=1;
            }
            ratePlayers();
            int i=1;
            for(Player p:allplayers){
                if(i%2!=0)
                    team1.add(p);
                else
                    team2.add(p);
                i+=1;
            }
            //copy P players from each team to team on court
            i=1;
            for(Player p: team1){
                team1onthecourt.add(p);

                i+=1;
                if(i>P)
                    break;
            }
            for(Player p: team1onthecourt){
                team1.remove(p);
            }
            i=1;
            for(Player p: team2){
                team2onthecourt.add(p);
                //team2.remove(p);
                i+=1;
                if(i>P)
                    break;
            }
            for(Player p: team2onthecourt){
                team2.remove(p);
            }
            int currentRotation=1;
            while(currentRotation<=M){
                for(Player p:team1onthecourt){
                    p.setTimespentoncourt(p.getTimespentoncourt()+1);
                }
                for(Player p:team2onthecourt){
                    p.setTimespentoncourt(p.getTimespentoncourt()+1);
                }

                removeplayerfromcourt();
                addplayertocourt();

                currentRotation+=1;
            }

        }
        catch(IOException e){

        }
    }

    public void ratePlayers(){
        Collections.sort(allplayers,new Comparator<Player>() {
            @Override
            public int compare(Player player, Player player2) {
                if(player.getShotpercentage()<player2.getShotpercentage())
                    return 1;
                if(player.getShotpercentage()>player2.getShotpercentage())
                    return -1;
                if(player.getHeight()<player2.getHeight())
                    return 1;
                if(player.getHeight()>player2.getHeight())
                    return -1;
                return 0;
            }
        });

        int i=1;
        for(Player p: allplayers){
            p.setDraftnumber(i);
            i+=1;

        }

    }

    public void removeplayerfromcourt(){
        int maxtime=0;
        int maxdraftnumber=0;
        for(Player p:team1onthecourt){
            if(p.getTimespentoncourt()>maxtime)
                maxtime=p.getTimespentoncourt();

        }
        int i=0;
        int maxindex=0;
        for(Player p:team1onthecourt){
            if(p.getTimespentoncourt()==maxtime && p.getDraftnumber()>maxdraftnumber){
                maxtime=p.getTimespentoncourt();
                maxdraftnumber=p.getDraftnumber();
                maxindex=i;
            }
            i+=1;

        }
        Player p1=team1onthecourt.get(maxindex);
        team1onthecourt.remove(maxindex);
        team1.add(p1);

        maxtime=0;
        maxdraftnumber=0;
        for(Player p:team2onthecourt){
            if(p.getTimespentoncourt()>maxtime)
                maxtime=p.getTimespentoncourt();

        }
        i=0;
        maxindex=0;
        for(Player p:team2onthecourt){
            if(p.getTimespentoncourt()==maxtime && p.getDraftnumber()>maxdraftnumber){
                maxtime=p.getTimespentoncourt();
                maxdraftnumber=p.getDraftnumber();
                maxindex=i;
            }
            i+=1;

        }
        Player p=team2onthecourt.get(maxindex);
        team2onthecourt.remove(maxindex);
        team2.add(p);

    }

    public void addplayertocourt(){
        int mintime=Integer.MAX_VALUE;
        int mindraftnumber=Integer.MAX_VALUE;
        for(Player p:team1){
            if(p.getTimespentoncourt()<mintime)
                mintime=p.getTimespentoncourt();

        }
        int i=0;
        int minindex=0;
        for(Player p:team1){
            if(p.getTimespentoncourt()==mintime && p.getDraftnumber()<mindraftnumber){
                mintime=p.getTimespentoncourt();
                mindraftnumber=p.getDraftnumber();
                minindex=i;
            }
            i+=1;

        }
        Player p1=team1.get(minindex);
        team1.remove(minindex);
        team1onthecourt.add(p1);

        mintime=Integer.MAX_VALUE;
        mindraftnumber=Integer.MAX_VALUE;
        for(Player p:team2){
            if(p.getTimespentoncourt()<mintime)
                mintime=p.getTimespentoncourt();

        }
        i=0;
        minindex=0;
        for(Player p:team2){
            if(p.getTimespentoncourt()==mintime && p.getDraftnumber()<mindraftnumber){
                mintime=p.getTimespentoncourt();
                mindraftnumber=p.getDraftnumber();
                minindex=i;
            }
            i+=1;

        }
        Player p=team2.get(minindex);
        team2.remove(minindex);
        team2onthecourt.add(p);

    }


    public void printplayersoncourt(int currentTestCase){
        ArrayList<String> players=new ArrayList<String>();
        for(Player p:team1onthecourt){
            players.add(p.getName());

        }
        for(Player p:team2onthecourt){
            players.add(p.getName());

        }
        Collections.sort(players);
        System.out.print("Case #"+currentTestCase+": ");
        for(String name:players){
              System.out.print(name+" ");
        }

    }




}
