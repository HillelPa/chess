import java.util.Iterator;
import java.util.LinkedList;

public class test {
    public static void main (String [] args){
       LinkedList<Piece> l1 = new LinkedList<Piece>();
        l1.add(new Tour(0,56,true));
        l1.add(new Cavalier(1,57,true));
        l1.add(new Fou(2,58,true));
        l1.add(new Reine(3,59,true));
        l1.add(new Roi(4,60,true));
        l1.add(new Fou(5,61,true));
        l1.add(new Cavalier(6,62,true));
        l1.add(new Tour(7,63,true));

     Iterator<Piece> it = l1.iterator();
        while(it.hasNext()){
         Piece p = it.next();
         System.out.println("P : "+p);
          if(p.num == 58)
           l1.remove(p);
        }
        System.out.println(l1);
    }
}
