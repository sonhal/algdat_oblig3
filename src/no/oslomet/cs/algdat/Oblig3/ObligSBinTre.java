package no.oslomet.cs.algdat.Oblig3;

////////////////// ObligSBinTre /////////////////////////////////
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;


public class ObligSBinTre<T> implements Beholder<T>
{
    private static final class Node<T> {
        // en indre nodeklasse
        private T verdi;
        // nodens verdi
        private Node<T> venstre, høyre;
        // venstre og høyre barn
        private Node<T> forelder;
        // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
        {
            this.verdi = verdi;
            venstre = v; høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)
        {
            this(verdi, null, null, forelder);
        }
        // konstruktør

        @Override
        public String toString(){ return "" + verdi;}

    } // class Node


    private Node<T> rot;
    private int antall;
    private int endringer; // peker til rotnoden
    // antall noder
    // antall endringer
    private final Comparator<? super T> comp; // komparator



    public ObligSBinTre(Comparator<? super T> c) // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<T>(verdi, q);                   // oppretter en ny node


        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }

    @Override
    public boolean inneholder(T verdi)
    {
        if (verdi == null) return false; // hvis verdien er null såp er den ikke i treet
        Node<T> p = rot;
        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }

    @Override
    public boolean fjern(T verdi)
    {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                rot = b;
            }
            else if (p == q.venstre){
                q.venstre = b;
                if(b != null){
                    b.forelder = q;
                }
            }
            else {
                q.høyre = b;
                if(b != null){
                    b.forelder = q;
                }
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) {
                s.venstre = r.høyre;
                if(r.høyre != null){
                    r.høyre.forelder = s;
                }

            }
            else {
                s.høyre = r.høyre;
                if(r.høyre != null){
                    r.høyre.forelder = s;
                }
            }
        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi)
    {
        if(tom()) return 0;
        int count = 0;
        while (fjern(verdi)){
            count++;
        }
        return count;
    }

    @Override
    public int antall()
    {
        return antall;
    }

    public int antall(T verdi)
    {
        int count = 0;

        if (verdi == null) return 0;
        Node<T> p = rot;
        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else{
                count++ ;
                p = p.høyre;
            }
        }
        return count;
    }

    @Override
    public boolean tom(){
        return antall == 0;
    }

    @Override
    public void nullstill()
    {
        slettTree(rot);
        rot = null;
        antall = 0;
    }

    private static <T> void slettTree(Node<T> p)
    {
        if (p == null) return;

        slettTree(p.venstre);
        slettTree(p.høyre);

        // nullstill
        p.verdi = null;
        p.venstre = null;
        p.høyre = null;
        p.forelder = null;
    }

    private static <T> Node<T> nesteInorden(Node<T> p)
    {
        if (p.høyre != null)  // p har høyre barn
        {
            return forsteInorden(p.høyre);
        }
        else  // må gå oppover i treet
        {
            while (p.forelder != null && p.forelder.høyre == p)
            {
                p = p.forelder;
            }
            return p.forelder;
        }
    }

    private static <T> Node<T> forsteInorden(Node<T> p)
    {
        while (p.venstre != null) p = p.venstre;
        return p;
    }

    private void reverseInorden(Consumer<? super T> oppgave)  // iterativ inorden
    {
        if (tom()) return;            // tomt tre

        Stack<Node<T>> stakk = new Stack<>();
        Node<T> p = rot;   // starter i roten og går til venstre
        for ( ; p.høyre != null; p = p.høyre) stakk.add(p);

        while (true)
        {
            oppgave.accept(p.verdi);      // oppgaven utføres

            if (p.venstre!= null)          // til venstre i høyre subtre
            {
                for (p = p.venstre; p.høyre != null; p = p.høyre)
                {
                    stakk.add(p);
                }
            }
            else if (!stakk.empty())
            {
                p = stakk.pop();   // p.høyre == null, henter fra stakken
            }
            else break;          // stakken er tom - vi er ferdig

        } // while
    }



    @Override
    public String toString()
    {
        if(tom()) return "[]";

        StringJoiner builder = new StringJoiner(", ", "[", "]");
        Node<T> p = forsteInorden(rot);  // den aller første i inorden

        while (p != null)
        {
            builder.add(p.verdi.toString());
            p = nesteInorden(p);
        }
        return builder.toString();
    }

    public String omvendtString()
    {
        StringJoiner builder = new StringJoiner(", ", "[", "]");
        reverseInorden(x -> builder.add(x.toString()));
        return builder.toString();
    }

    public String høyreGren()
    {
        if(rot == null) return "[]";

        Node<T> node = rot;
        StringJoiner builder = new StringJoiner(", ", "[", "]");
        ArrayList<T> path = new ArrayList<>();


        path.add(node.verdi);

        if(node.venstre==null && node.høyre==null)
        {
            return path.toString();
        }
        else
        {
            while (true){
                if(node.høyre != null) node = node.høyre;
                else if(node.venstre != null) node = node.venstre;
                else break;
                path.add(node.verdi);
            }
        }
        return path.toString();
    }

    public String lengstGren()
    {
        if(tom()) return "[]";
        ArrayList<ArrayList<T>> paths
                = allRootToLeafPaths(rot, new ArrayList<>(), new ArrayList<>());

        ArrayList<T> longest = new ArrayList<>();

        for (ArrayList<T> el:
                paths) {
            if (el.size() > longest.size()) longest = el;
        }
        return longest.toString();
    }

    public String[] grener()
    {
        if(tom()) return new String[0];
        ArrayList<ArrayList<T>> paths
                = allRootToLeafPaths(rot, new ArrayList<>(), new ArrayList<>());

        String[] list = new String[paths.size()];
        int counter = 0;
        for (ArrayList<T> el:
             paths) {
            list[counter] = el.toString();
            counter++;
        }
        return list;
    }

    private ArrayList<ArrayList<T>> allRootToLeafPaths(Node<T> node, ArrayList<ArrayList<T>> paths, ArrayList<T> path)
    {
        if(node==null)
        {
            return null;
        }
        path.add(node.verdi);

        if(node.venstre==null && node.høyre==null)
        {

            paths.add(path);
        }
        else
        {
            allRootToLeafPaths(node.venstre, paths, new ArrayList<>(path));
            allRootToLeafPaths(node.høyre, paths, new ArrayList<>(path));
        }
        return paths;
    }

    public String bladnodeverdier()
    {
        if(tom()) return "[]";
        ArrayList<T> list = new ArrayList<>();
        leafNodeValues(list, rot);
        return list.toString();
    }

    private static <T> void leafNodeValues(ArrayList<T> leafs, Node<T> node){
        if(node.venstre == null && node.høyre == null) leafs.add(node.verdi);
        if(node.venstre != null) leafNodeValues(leafs, node.venstre);
        if(node.høyre != null)leafNodeValues(leafs, node.høyre);
    }

    public String postString()
    {
        if (tom()) return "[]";
        ArrayList<T> list = new ArrayList<>();

        Stack<Node<T>> stakk = new Stack<>();
        Node<T> p = rot;
        stakk.push(p);
        Node<T> forrige = null;

        while (!stakk.empty())
        {
            Node<T> current = stakk.peek();

            if (forrige == null || forrige.venstre == current ||
                    forrige.høyre == current)
            {
                if (current.venstre != null)
                    stakk.push(current.venstre);
                else if (current.høyre != null)
                    stakk.push(current.høyre);
                else
                {
                    stakk.pop();
                    list.add(current.verdi);
                }
            }
            else if (current.venstre == forrige) {
                if (current.høyre != null)
                    stakk.push(current.høyre);
                else {
                    stakk.pop();
                    list.add(current.verdi);
                }
            }
            else if (current.høyre == forrige)
            {
                stakk.pop();
                list.add(current.verdi);
            }

            forrige = current;
        }
        return list.toString();
    }

    @Override
    public Iterator<T> iterator() {return new BladnodeIterator(); }

    private class BladnodeIterator implements Iterator<T> {
        private Node<T> p = rot, q = null;
        private boolean removeOK = false;
        private int iteratorendringer = endringer;
        private BladnodeIterator() // konstruktør
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public boolean hasNext()
        {
            return p != null; // Denne skal ikke endres!
        }

        @Override
        public T next()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Ikke kodet ennå!");
        }

    } // BladnodeIterator
} // ObligSBinTre
