package snpe;

import snpe.generators.SparseNoisyPeriodicSignal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pubsim.distributions.Gaussian;
import pubsim.distributions.discrete.Poisson;
import snpe.generators.DifferencesIID;

/**
 *
 * @author Robby McKilliam
 */
public class PeriodogramEstimatorTest {
    
    public PeriodogramEstimatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of estimate method, of class NormalisedSamplingLLS.
     */
    @Test
    public void testEstimate() {
        System.out.println("Periodogram estimator");

        double Tmin = 0.7;
        double Tmax = 1.3;
        int n = 30;
        double T = 1.1;
        double phase = 0.4;
        PeriodogramEstimator instance = new PeriodogramEstimator(n, Tmin, Tmax, 2*n);

        double noisestd = 0.001;
        Gaussian noise = new Gaussian(0.0,noisestd*noisestd);
        Poisson drv = new Poisson(2);
        
        SparseNoisyPeriodicSignal sig = new SparseNoisyPeriodicSignal(n,T, phase, new DifferencesIID(n,drv),noise);
        double[] y = sig.generate();

        instance.estimate(y);
        System.out.println(instance.period() + "\t" + instance.phase());
        assertEquals(T, instance.period(), 0.001);
        assertEquals(phase, instance.phase(), 0.001);
    }
    
}