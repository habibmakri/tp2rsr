package tp2tcp;
import java.net.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
// serveur multi thread
public class Server2 implements Runnable {
	
	private Socket socket;
	private int nb;
	public Server2(Socket socket, int nb)
	{
		this.socket = socket;
		this.nb     = nb;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	try {
	
		
		ArrayList<users> db = new ArrayList<users>();
		db.add(new users("habib","makri"));
		db.add(new users("merioua","java"));
		
		ObjectOutputStream  out = new ObjectOutputStream (socket.getOutputStream());
		ObjectInputStream   in = new ObjectInputStream  (socket.getInputStream());
		int f = -1;
		while(f<1) {
			
		String nomu =  in.readObject().toString();
		out.writeObject(1);		
		String motp =  in.readObject().toString();
		for (int i=0;i<db.size();i++) {
			if(db.get(i).name.equals(nomu)) {
				if(db.get(i).password.equals(motp)) {
					
					f=1;
					break;
				}else {
					f=0;
				}
			}
		}
		if(f == -1) {
			db.add(new users(nomu, motp));
			f=-2;
		}
		out.writeObject(f);
		}
		
		int o= (int)in.readObject();
		out.writeObject(1);
		
		
		if(o == 1) {
			int[][] a= (int[][])in.readObject();
			out.writeObject(1);
			int[][] b= (int[][])in.readObject();
			int[][] c= sommematrix(a,b);
			out.writeObject(c);
			System.out.println("somme envoyé ");
		}else if(o == 2){
			int[][] a= (int[][])in.readObject();
			out.writeObject(1);
			int[][] b= (int[][])in.readObject();
			int[][] c= multimatrix(a,b);
			out.writeObject(c);
			System.out.println("multi envoyé ");
		}else {
			int[][] a= (int[][])in.readObject();
			out.writeObject(1);
			int[][] b= (int[][])in.readObject();
			int[][] c= transmatrix(a);
			out.writeObject(c);
			System.out.println("trans envoyé ");
		}
		
	
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[][] sommematrix(int[][] a,int[][] b) throws RemoteException{
	 int[][] s = new int[a.length][a[0].length];
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[0].length;j++) {
				s[i][j] = a[i][j] + b[i][j];
			}
		}
		
		return s;
	}
	
	public int[][] multimatrix(int[][] matrix1,int[][] matrix2)throws RemoteException  {
	    int rows1 = matrix1.length;
	    int columns1 = matrix1[0].length;
	    int rows2 = matrix2.length;
	    int columns2 = matrix2[0].length;

	    if (columns1 != rows2) {
	        throw new IllegalArgumentException("Matrices cannot be multiplied: Invalid dimensions");
	    }

	    int[][] result = new int[rows1][columns2];

	    for (int i = 0; i < rows1; i++) {
	        for (int j = 0; j < columns2; j++) {
	            for (int k = 0; k < columns1; k++) {
	                result[i][j] += matrix1[i][k] * matrix2[k][j];
	            }
	        }
	    }

	    return result;
	}
	public int[][] transmatrix(int[][] matrix)throws RemoteException {
	    int rows = matrix.length;
	    int columns = matrix[0].length;

	    int[][] transpose = new int[columns][rows];

	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < columns; j++) {
	            transpose[j][i] = matrix[i][j];
	        }
	    }

	    return transpose;
	}
	 
	
}
