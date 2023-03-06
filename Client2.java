package tp2tcp;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client2 {
	static int port = 9000;
	static String server_name= "localhost";
	public static void main(String[] args) throws Exception{
		
		
		Socket socket = new Socket(server_name,port);
		Scanner scan = new Scanner(System.in);
		int[][] a ;
		int[][] b ;
		int f=0;
		ObjectOutputStream  out = new ObjectOutputStream (socket.getOutputStream());
		ObjectInputStream   in = new ObjectInputStream  (socket.getInputStream());
		while(f == 0) {
		System.out.println("taper le nom d'utilusateur: ");
		String nomu = scan.nextLine();
		out.writeObject(nomu);
		in.readObject();
		System.out.println("taper le mot du passe: ");
		String motp = scan.nextLine();
		out.writeObject(motp);
		int state = (int) in.readObject();
		if (state == 1) {
			System.out.println("status connecter ");
			f = 1;
		}else if(state == -2){
			System.out.println("profile enrigistre restaper pour connecter ");
		}else {
			System.out.println("err ressayer ");
			f=0;
		}
		}
		int o=0;
		while(o != 1  && o != 2 && o != 3) {
			System.out.println("Choisissez l'opertarion: 1- addition 2-multiplication 3-transposé");
			o = scan.nextInt();
		}
		if(o == 1  || o == 2 ) {
			System.out.println("taper le nombre des lignes des matrices: ");
			int I = scan.nextInt();
			System.out.println("taper le nombre des collones des matrices: ");
			int J = scan.nextInt();
			a = new int[I][J];
			b = new int[I][J];
			for(int i=0 ;i<I;i++) {
				for(int j=0;j<J;j++) {
					System.out.println("taper l'element ["+(i+1)+","+(j+1)+"] du Premiere matrice: ");
					a[i][j] = scan.nextInt();
				}
			}
			for(int i=0 ;i<I;i++) {
				for(int j=0;j<J;j++) {
					System.out.println("taper l'element ["+(i+1)+","+(j+1)+"] du Deuxieme matrice: ");
					b[i][j] = scan.nextInt();
				}
			}
			out.writeObject(o);
			in.readObject();
			out.writeObject(a);
			in.readObject();
			out.writeObject(b);
			int[][] c= (int[][])in.readObject();
			System.out.println("la matrice resultant est: ");
			for(int i=0 ;i<c.length;i++) {
				for(int j=0;j<c[0].length;j++) {
					System.out.print("   "+c[i][j]);
					
				}System.out.print("\n");
			}
		}else {
			System.out.println("taper le nombre des lignes du matrice: ");
			int I = scan.nextInt();
			System.out.println("taper le nombre des collones du matrice: ");
			int J = scan.nextInt();
			a = new int[I][J];
			for(int i=0 ;i<I;i++) {
				for(int j=0;j<J;j++) {
					System.out.println("taper l'element ["+(i+1)+","+(j+1)+"] du matrice: ");
					a[i][j] = scan.nextInt();
				}
			}
			out.writeObject(o);
			in.readObject();
			out.writeObject(a);
			in.readObject();
			out.writeObject(a);
			int[][] c= (int[][])in.readObject();
			System.out.println("la matrice resultant est: ");
			for(int i=0 ;i<c.length;i++) {
				for(int j=0;j<c[0].length;j++) {
					System.out.print("   "+c[i][j]);
					
				}System.out.print("\n");
			}
			}		
		

		

	}
}
