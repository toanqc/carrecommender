package mum.bigdata.car.recommender.util;

import java.io.*;
import java.util.*;

/** The class encapsulates an implementation of the Apriori algorithm
 * to compute frequent itemsets.
 * 
 * Datasets contains integers (>=0) separated by spaces, one transaction by line, e.g.
 * 1 2 3
 * 0 9
 * 1 9
 * 
 * Usage with the command line : 
 *   $ java mining.Apriori fileName support
 *   $ java mining.Apriori /tmp/data.dat 0.8
 *   $ java mining.Apriori /tmp/data.dat 0.8 > frequent-itemsets.txt
 *   
 * Usage as library: see {@link ExampleOfClientCodeOfApriori}
 * 
 * @source Martin Monperrus, University of Darmstadt, 2010
 * @source Nathan Magnus and Su Yibin, under the supervision of Howard Hamilton, University of Regina, June 2009.
 * @copyright GNU General Public License v3
 * No reproduction in whole or part without maintaining this copyright notice
 * and imposing this condition on any subsequent users.
 */
public class Apriori extends Observable {


    public static void main(String[] args) throws Exception {
    	String[] arr = {"C:\\Users\\Admin\\Downloads\\retail.dat", "0.3"};
        Apriori ap = new Apriori(arr);
    }

    /** the list of current itemsets */
    private List<int[]> itemsets ;
    /** the name of the transcation file */
    private String transaFile; 
    /** number of different items in the dataset */
    private int numItems; 
    /** total number of transactions in transaFile */
    private int numTransactions; 
    /** minimum support for a frequent itemset in percentage, e.g. 0.8 */
    private double minSup; 
    
    /** by default, Apriori is used with the command line interface */
    private boolean usedAsLibrary = false;

    /** This is the main interface to use this class as a library */
    public  Apriori(String[] args, Observer ob) throws Exception
    {
    	usedAsLibrary = true;
    	configure(args);
    	this.addObserver(ob);
    	go();
    }

    /** generates the apriori itemsets from a file
     * 
     * @param args configuration parameters: args[0] is a filename, args[1] the min support (e.g. 0.8 for 80%)
     */
    public  Apriori(String[] args) throws Exception
    {
        configure(args);
        go();
    }

    /** starts the algorithm after configuration */
    private void go() throws Exception {
        //start timer
        long start = System.currentTimeMillis();

        // first we generate the candidates of size 1
        createItemsetsOfSize1();        
        int itemsetNumber=1; //the current itemset being looked at
        int nbFrequentSets=0;
        
        while (itemsets.size()>0)
        {

            calculateFrequentItemsets();

            if(itemsets.size()!=0)
            {
                nbFrequentSets+=itemsets.size();
                log("Found "+itemsets.size()+" frequent itemsets of size " + itemsetNumber + " (with support "+(minSup*100)+"%)");;
                createNewItemsetsFromPreviousOnes();
            }

            itemsetNumber++;
        } 

        //display the execution time
        long end = System.currentTimeMillis();
        log("Execution time is: "+((double)(end-start)/1000) + " seconds.");
        log("Found "+nbFrequentSets+ " frequents sets for support "+(minSup*100)+"% (absolute "+Math.round(numTransactions*minSup)+")");
        log("Done");
        
        System.out.println(Arrays.deepToString(itemsets.toArray()));    
    }

    /** triggers actions if a frequent item set has been found  */
    private void foundFrequentItemSet(int[] itemset, int support) {
    	if (usedAsLibrary) {
            this.setChanged();
            notifyObservers(itemset);
    	}
    	else {System.out.println(Arrays.toString(itemset) + "  ("+ ((support / (double) numTransactions))+" "+support+")");}
    }

    /** outputs a message in Sys.err if not used as library */
    private void log(String message) {
    	if (!usedAsLibrary) {
    		System.err.println(message);
    	}
    }

    /** computes numItems, numTransactions, and sets minSup */
    private void configure(String[] args) throws Exception
    {        
        // setting transafile
        if (args.length!=0) transaFile = args[0];
        else transaFile = "chess.dat"; // default
    	
    	// setting minsupport
    	if (args.length>=2) minSup=(Double.valueOf(args[1]).doubleValue());    	
    	else minSup = .8;// by default
    	if (minSup>1 || minSup<0) throw new Exception("minSup: bad value");
    	
    	
    	// going thourgh the file to compute numItems and  numTransactions
    	numItems = 0;
    	numTransactions=0;
    	BufferedReader data_in = new BufferedReader(new FileReader(transaFile));
    	while (data_in.ready()) {    		
    		String line=data_in.readLine();
    		if (line.matches("\\s*")) continue; // be friendly with empty lines
    		numTransactions++;
    		StringTokenizer t = new StringTokenizer(line," ");
    		while (t.hasMoreTokens()) {
    			int x = Integer.parseInt(t.nextToken());
    			//log(x);
    			if (x+1>numItems) numItems=x+1;
    		}    		
    	}  
    	
        outputConfig();

    }

   /** outputs the current configuration
     */ 
	private void outputConfig() {
		//output config info to the user
		 log("Input configuration: "+numItems+" items, "+numTransactions+" transactions, ");
		 log("minsup = "+minSup+"%");
	}

	/** puts in itemsets all sets of size 1, 
	 * i.e. all possibles items of the datasets
	 */
	private void createItemsetsOfSize1() {
		itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
        	int[] cand = {i};
        	itemsets.add(cand);
        }
	}
			
    /**
     * if m is the size of the current itemsets,
     * generate all possible itemsets of size n+1 from pairs of current itemsets
     * replaces the itemsets of itemsets by the new ones
     */
    private void createNewItemsetsFromPreviousOnes()
    {
    	// by construction, all existing itemsets have the same size
    	int currentSizeOfItemsets = itemsets.get(0).length;
    	log("Creating itemsets of size "+(currentSizeOfItemsets+1)+" based on "+itemsets.size()+" itemsets of size "+currentSizeOfItemsets);
    		
    	HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>(); //temporary candidates
    	
        // compare each pair of itemsets of size n-1
        for(int i=0; i<itemsets.size(); i++)
        {
            for(int j=i+1; j<itemsets.size(); j++)
            {
                int[] X = itemsets.get(i);
                int[] Y = itemsets.get(j);

                assert (X.length==Y.length);
                
                //make a string of the first n-2 tokens of the strings
                int [] newCand = new int[currentSizeOfItemsets+1];
                for(int s=0; s<newCand.length-1; s++) {
                	newCand[s] = X[s];
                }
                    
                int ndifferent = 0;
                // then we find the missing value
                for(int s1=0; s1<Y.length; s1++)
                {
                	boolean found = false;
                	// is Y[s1] in X?
                    for(int s2=0; s2<X.length; s2++) {
                    	if (X[s2]==Y[s1]) { 
                    		found = true;
                    		break;
                    	}
                	}
                	if (!found){ // Y[s1] is not in X
                		ndifferent++;
                		// we put the missing value at the end of newCand
                		newCand[newCand.length -1] = Y[s1];
                	}
            	
            	}
                
                // we have to find at least 1 different, otherwise it means that we have two times the same set in the existing candidates
                assert(ndifferent>0);
                
                
                if (ndifferent==1) {
                    // HashMap does not have the correct "equals" for int[] :-(
                    // I have to create the hash myself using a String :-(
                	// I use Arrays.toString to reuse equals and hashcode of String
                	Arrays.sort(newCand);
                	tempCandidates.put(Arrays.toString(newCand),newCand);
                }
            }
        }
        
        //set the new itemsets
        itemsets = new ArrayList<int[]>(tempCandidates.values());
    	log("Created "+itemsets.size()+" unique itemsets of size "+(currentSizeOfItemsets+1));

    }



    /** put "true" in trans[i] if the integer i is in line */
    private void line2booleanArray(String line, boolean[] trans) {
	    Arrays.fill(trans, false);
	    StringTokenizer stFile = new StringTokenizer(line, " "); //read a line from the file to the tokenizer
	    //put the contents of that line into the transaction array
	    while (stFile.hasMoreTokens())
	    {
	    	
	        int parsedVal = Integer.parseInt(stFile.nextToken());
			trans[parsedVal]=true; //if it is not a 0, assign the value to true
	    }
    }

    
    /** passes through the data to measure the frequency of sets in {@link itemsets},
     *  then filters thoses who are under the minimum support (minSup)
     */
    private void calculateFrequentItemsets() throws Exception
    {
    	
        log("Passing through the data to compute the frequency of " + itemsets.size()+ " itemsets of size "+itemsets.get(0).length);

        List<int[]> frequentCandidates = new ArrayList<int[]>(); //the frequent candidates for the current itemset

        boolean match; //whether the transaction has all the items in an itemset
        int count[] = new int[itemsets.size()]; //the number of successful matches, initialized by zeros


		// load the transaction file
		BufferedReader data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transaFile)));

		boolean[] trans = new boolean[numItems];
		
		// for each transaction
		for (int i = 0; i < numTransactions; i++) {

			// boolean[] trans = extractEncoding1(data_in.readLine());
			String line = data_in.readLine();
			line2booleanArray(line, trans);

			// check each candidate
			for (int c = 0; c < itemsets.size(); c++) {
				match = true; // reset match to false
				// tokenize the candidate so that we know what items need to be
				// present for a match
				int[] cand = itemsets.get(c);
				//int[] cand = candidatesOptimized[c];
				// check each item in the itemset to see if it is present in the
				// transaction
				for (int xx : cand) {
					if (trans[xx] == false) {
						match = false;
						break;
					}
				}
				if (match) { // if at this point it is a match, increase the count
					count[c]++;
					//log(Arrays.toString(cand)+" is contained in trans "+i+" ("+line+")");
				}
			}

		}
		
		data_in.close();

		for (int i = 0; i < itemsets.size(); i++) {
			// if the count% is larger than the minSup%, add to the candidate to
			// the frequent candidates
			if ((count[i] / (double) (numTransactions)) >= minSup) {
				foundFrequentItemSet(itemsets.get(i),count[i]);
				frequentCandidates.add(itemsets.get(i));
			}
			//else log("-- Remove candidate: "+ Arrays.toString(candidates.get(i)) + "  is: "+ ((count[i] / (double) numTransactions)));
		}

        //new candidates are only the frequent candidates
        itemsets = frequentCandidates;
    }
}