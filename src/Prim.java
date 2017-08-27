import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Prim {
	
	FileReader fr;
	BufferedReader br;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prim obj=new Prim();
		int arr[][]=obj.readFile("prim_edges");
		System.out.println(obj.prim(arr));
	}
	public int prim(int adjMat[][]) {
		int n = Array.getLength(adjMat)-1;
		int X[];
		int V[];
		int A[];		
		X = new int[n+1];
		V = new int[n+1];
		A = new int[n+1];
		for(int i=0;i<=n;i++) {
			V[i]=1;X[i]=0;
		}
		
		int terminate=1,now=1,cnt=0;
		X[now]=1;
		A[now]=0;
		int w = 1,min=999999, minInd=0;
		String status="none";
		int cost=0;
		while(true) {
			status="none";
			min=999999;
			for(int j=1;j<=n;j++) {
				if(X[j]==1) {
					now=j;
					for(int i=1;i<=n;i++) {
						if(adjMat[now][i]!=0 && i!=now) {
							status="ok";
							if(X[i]==0 && X[now]==1) {						 
								if(min > adjMat[now][i]) {
									min=adjMat[now][i];
									minInd=i;
								}
								
							}
						}
					}
					
				}
			}
//			if(status=="none") {
//				break;
//			}
			cost=cost+min;
			A[minInd]=min;
			X[minInd]=1;
//			System.out.println(cnt++ + ";" + terminate + ";" + minInd);
			now=minInd;
			terminate=1;
			for(int i=1;i<=n;i++) {
				if(X[i]==0) {
					terminate=0;
				}
			}
			if(terminate==1) {
				break;
			}
//			if(cnt==1000) {
//				break;
//			}
		}
		return cost;
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
			
			int n = Integer.parseInt(fileList.get(0).split(" ")[0]);
//			System.out.println(n);
			int[][] adjMat= new int[n+1][n+1];
			int k=0;
			
			for(int j=1;j<fileList.size();j++) {
				adjMat[Integer.parseInt(fileList.get(j).split(" ")[0])][Integer.parseInt(fileList.get(j).split(" ")[1])]=Integer.parseInt(fileList.get(j).split(" ")[2]);
				adjMat[Integer.parseInt(fileList.get(j).split(" ")[1])][Integer.parseInt(fileList.get(j).split(" ")[0])]=Integer.parseInt(fileList.get(j).split(" ")[2]);
			}
			
			return adjMat;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
