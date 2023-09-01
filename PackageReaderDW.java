import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackageReaderDW implements PackageReaderInterface{

    Double[][] weightsMatrix;
    int index;

    ArrayList<PackageCenterDW> aoc = new ArrayList<PackageCenterDW>();

    public ArrayList<PackageCenterDW> read(File filename) throws FileNotFoundException {
        Scanner fscan = new Scanner(filename);
        int n = 0;
        while(fscan.hasNextLine()) {

            n++;
            fscan.nextLine();
            //System.out.println(n);
        }


        weightsMatrix = new Double[n*2][n*2];
        index= 0;


        fscan = new Scanner(filename);
        int matrixPos= 0;
        fscan.nextLine();
        while(fscan.hasNextLine()) {


            String s = fscan.nextLine();
            if(s.trim().equals("}")){
                //System.out.println("END");
                break;
            }
            //String s = "A -- C [label=\"1\"]; ";
            String place1 = s.substring(0, s.indexOf("-")).trim();

            s = s.substring(s.indexOf("-")+1, s.length());

            String place2 = s.substring(s.indexOf("-")+1, s.indexOf("[")).trim();
            Double weight = Double.valueOf(s.substring(s.indexOf("\"")+1, s.indexOf("\"", s.indexOf("\"")+1)));

            //System.out.println(e.getName() + " " + e2.getName());	
            boolean containsE = false;
            boolean containsE2 = false;
            for(int i = 0; i < aoc.size();i++) {
                if(aoc.get(i).getName().equals(place1)) {
                    containsE  =true;

                }
                if(aoc.get(i).getName().equals(place2)) {
                    containsE2 = true;

                }
            }
            PackageCenterDW e = null;;
            if(!containsE) {
                //System.out.println(e.getName());
                e = new PackageCenterDW(place1, index);
                aoc.add(e);
                addPackageCenter(e);
                index++;
            }else {
                for( PackageCenterDW p : aoc) {
                    if(p.getName().equals(place1)) {
                        e = p;
                    }
                }
            }
            PackageCenterDW e2 = null;;
            if(!containsE2) {
                //System.out.println(e2.getName());
                e2 = new PackageCenterDW(place2, index);
                aoc.add(e2);
                addPackageCenter(e2);
                index++;
            }else {
                for( PackageCenterDW p : aoc) {
                    if(p.getName().equals(place2)) {
                        e2 = p;
                    }
                }
            }

            addEdge(e, e2, weight);


        }
        cleanMatrix();
        return aoc;
    }
    private void cleanMatrix() {
        Double[][] m;
        int size = weightsMatrix.length;
        for(int i =0; i < weightsMatrix.length;i++) {
            boolean empty = true;
            for(int j = 0; j< weightsMatrix.length;j++) {
                if(weightsMatrix[i][j]!=null) {



                    if(weightsMatrix[i][j]!=0.0) {
                        empty = false;
                    }
                }
            }
            if(empty) {
                size--;
            }
        }
        m = new Double[size][size];
        for(int i = 0; i < size;i++) {
            for(int j = 0; j< size;j++) {
                m[i][j] = weightsMatrix[i][j];
            }
        }
        weightsMatrix = m;
    }


    private void addPackageCenter(PackageCenterDW p) {
        if (index>weightsMatrix.length) {
            throw new IndexOutOfBoundsException("matrix is full");

        }
        p.setMatrixPosition(index);
        //System.out.println(p.getName() + " " + p.getMatrixPosition());


    }

    private void addEdge(PackageCenterDW p1, PackageCenterDW p2, double weight) {
        if(p1.getMatrixPosition()<0||p2.getMatrixPosition()<0) {
            throw new IndexOutOfBoundsException("package centers haven't been added to the matrix yet");

        }
        weightsMatrix[p1.getMatrixPosition()][p2.getMatrixPosition()] = weight;
        weightsMatrix[p2.getMatrixPosition()][p1.getMatrixPosition()] = weight;

    }

    public double getWeight(String place1, String place2) {
        //System.out.println("here");
        PackageCenterDW p1 = null;
        PackageCenterDW p2 = null;
        for(PackageCenterDW p: aoc) {
            if(p.getName().equals(place1)) {
                //System.out.println("HERE 1");
                p1 = p;
            }
            if(p.getName().equals(place2)) {
                //System.out.println("HERE 2");
                p2 = p;
            }
        }//System.out.println(p1.getName()+ " "+  p2.getName());
        return weightsMatrix[p1.getMatrixPosition()][p2.getMatrixPosition()];
    }

    public Double[][] getWeightsMatrix(){
        return weightsMatrix;
    }

    public int getNeighborCount(PackageCenterDW p1) {
        int nc = 0;
        for(int i = 0; i < weightsMatrix.length;i++) {
            if(weightsMatrix[p1.getMatrixPosition()][i]!=null) {
                if(weightsMatrix[p1.getMatrixPosition()][i]>0) {
                    nc++;
                }
            }
        }
        return nc;

    }
    public List<PackageCenterDW> getNeighbors(PackageCenterDW p1) {
        ArrayList<PackageCenterDW> aop = new ArrayList<PackageCenterDW>();
        for(int i = 0; i < weightsMatrix.length;i++) {
            if(weightsMatrix[p1.getMatrixPosition()][i]!=null) {
                if(weightsMatrix[p1.getMatrixPosition()][i]>0) {
                    //System.out.println("here");
                    for(int j =0; j <aoc.size();j++) {

                        if(aoc.get(j).getMatrixPosition()==i) {
                            //System.out.println("here 2");
                            aop.add(aoc.get(j));
                        }
                    }
                }
            }
        }
        return aop;
    }




}
