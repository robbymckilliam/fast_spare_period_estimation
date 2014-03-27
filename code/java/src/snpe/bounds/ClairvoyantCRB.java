package snpe.bounds;

import pubsim.lattices.Anstar.Anstar;
import pubsim.VectorFunctions;

/**
 * This is Sirdopulous's clairvoyant Cramer-Rao bound.  It is
 * just the bound given by linear regression under the assumption
 * that the vector of indicies, s, is known.
 * @author Robby McKilliam
 */
public class ClairvoyantCRB implements BoundCalculator {
    
    protected double[] s, x;
    
    protected double T, var, mag2x;
    
    /** Set the vector of indicies */
    public void setS(double[] s){
        this.s = s;
        x = new double[s.length];
        Anstar.project(s, x);
        mag2x = VectorFunctions.sum2(x);
    }
    
    /** Set the true period of the signal */
    public void setPeriod(double T){
        this.T = T;
    }
    
    /** Set the variance of the noise */
    public void setVariance(double var){
        this.var = var;
    }
    
    /** Return the clairvoyant CRB for the set parameters */
    public double getBound(){
        return var/mag2x;
    }

    public void setGeometicParameter(double p) {
    }

    public void setLength(int N) {
    }
    
        
}
