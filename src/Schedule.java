import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	FileReader fr;
	BufferedReader br;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Schedule obj=new Schedule();
		int jobs[][]=obj.readFile("jobs");
		System.out.println(obj.scheduleSub(jobs));
		System.out.printf("%.0f",obj.scheduleRatio(jobs));
		
	}
	public double scheduleRatio(int[][] jobs) {
		int n=jobs.length;
		int jobsOrder[][]=new int[n][3];
		ArrayList<double[]> jobOrder=new ArrayList<double[]>();
		
		for(int i=0;i<n;i++) {
			double tmp[]=new double[3];
			tmp[0]=jobs[i][0];
			tmp[1]=jobs[i][1];
			tmp[2]=(double)jobs[i][0]/jobs[i][1];
			jobOrder.add(tmp);
										
		}

//		System.out.println(jobOrder.size());

		double sum=0,time=0,cnt=0;
		while(jobOrder.size()!=0) {
			double max=-999,maxWeight=0;
			int maxInd=0;
			for(int i=0;i<jobOrder.size();i++) {
				if(max<jobOrder.get(i)[2]) {
					max=jobOrder.get(i)[2];
					maxWeight=jobOrder.get(i)[0];
					maxInd=i;
//					System.out.println(maxInd);
					
				}
				else if(max==jobOrder.get(i)[2]&&maxWeight<jobOrder.get(i)[0]){
					max=jobOrder.get(i)[2];
					maxWeight=jobOrder.get(i)[0];
					maxInd=i;
					
				}
			}
			
//			System.out.println(jobOrder.get(maxInd)[0]+","+jobOrder.get(maxInd)[1]);
//			System.out.printf("%.20f\n",jobOrder.get(maxInd)[2]);
			cnt++;
			time=time+jobOrder.get(maxInd)[1];
			sum=sum+jobOrder.get(maxInd)[0]*time;
			jobOrder.remove(maxInd);
//			System.out.println(sum);
		}
//		System.out.println(cnt);
		return sum;
		
	}	
	public long scheduleSub(int[][] jobs) {
		int n=jobs.length;
		int jobsOrder[][]=new int[n][3];
		ArrayList<int[]> jobOrder=new ArrayList<int[]>();
		
		for(int i=0;i<n;i++) {
			int tmp[]=new int[3];
			tmp[0]=jobs[i][0];
			tmp[1]=jobs[i][1];
			tmp[2]=jobs[i][0]-jobs[i][1];
			jobOrder.add(tmp);
										
		}

		System.out.println(jobOrder.size());

		long sum=0,time=0,cnt=0;
		while(jobOrder.size()!=0) {
			int max=-999,maxInd=0, maxWeight=0;
			for(int i=0;i<jobOrder.size();i++) {
				if(max<jobOrder.get(i)[2]) {
					max=jobOrder.get(i)[2];
					maxWeight=jobOrder.get(i)[0];
					maxInd=i;
//					System.out.println(maxInd);
					
				}
				else if(max==jobOrder.get(i)[2]&&maxWeight<jobOrder.get(i)[0]){
					max=jobOrder.get(i)[2];
					maxWeight=jobOrder.get(i)[0];
					maxInd=i;
					
				}
			}
			
//			System.out.println(jobOrder.get(maxInd)[0]+","+jobOrder.get(maxInd)[1]+","+jobOrder.get(maxInd)[2]);
			cnt++;
			time=time+jobOrder.get(maxInd)[1];
			sum=sum+jobOrder.get(maxInd)[0]*time;
			jobOrder.remove(maxInd);
//			System.out.println(sum);
		}
		System.out.println(cnt);
		return sum;
		
	}
	public int[][] readFile(String filename) {
		try {
			fr = new FileReader("/home/stark/Documents/Workspace/java/" + filename + ".txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {		
			String i;
			
			List<String> fileList = new ArrayList<String>();
			
			while(true) {				
				i = br.readLine();
				if (i == null)
					break;
			
				fileList.add(i);
				

			}
			br.close();
			fr.close();
			
			int n = fileList.size();
			
			int arr[][] =new int[n-1][2];
			
			for(int j=0;j<n-1;j++) {
				arr[j][0]=Integer.parseInt(fileList.get(j+1).split(" ")[0]);
				arr[j][1]=Integer.parseInt(fileList.get(j+1).split(" ")[1]);
			}
			
			return arr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
