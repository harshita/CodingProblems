
import java.util.Arrays;
/**
 * User: Harshita Karande
 */

/**
 * Question: There are at n servers with capacity/memory limit.
 * There can be at most k tasks that need to be scheduled on those servers.
 * Each task requires certain capacity/memory to run, and each server can handle multiple tasks as long as the capacity limit is not hit.
 * This program returns true if all of the given tasks can be scheduled on the servers
 */
public class ServerTaskAllocation {

    public static void main(String args[]) {
        ServerTaskAllocation sta = new ServerTaskAllocation();
        boolean canAssign = sta.allocateTaskToServer(new int[] {8,16, 8, 32}, new int[] {18, 4, 8, 4, 6, 6, 8, 8 });
        System.out.println("Task can be allocated to servers? : "+canAssign);

    }

    public boolean allocateTaskToServer(int[] serverList, int[] taskList) {
        Arrays.sort(serverList);

        Arrays.sort(taskList);

        int totalServerCapacity = 0;
        int totalTaskCapacity = 0;

        for (int i = 0; i < serverList.length; i++) {
            totalServerCapacity += serverList[i];

        }
        for (int j = 0; j < taskList.length; j++) {
            totalTaskCapacity += taskList[j];
        }
        if (totalServerCapacity < totalTaskCapacity)
            return false;

        for (int i = taskList.length - 1; i >= 0; i--) {
            int j = serverList.length - 1;
            boolean isAssigned = false;
            int serverIndex = -1;
            while (j >= 0 && serverList[j] >= taskList[i]) {
                serverIndex = j;
                j--;

            }
            if (serverIndex != -1) {
                serverList[serverIndex]-=taskList[i];
                int temp = serverList[serverIndex];
                int k = serverIndex - 1;
                //Rearrange the server list
                while (k >= 0 && temp < serverList[k]) {
                    serverList[k+1] = serverList[k];
                    serverList[k] = temp;
                    k--;

                }

                isAssigned = true;
            }

            if(!isAssigned)
                return isAssigned;
        }
        return true;
    }
}
