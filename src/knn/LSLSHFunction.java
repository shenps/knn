package knn;

import java.util.Map.Entry;
import java.util.Random;

public class LSLSHFunction implements Hash<LSHRecord, Integer> {
	private double r[], w;
	private int d, b, M ;
	
	/**
	 * Constructor
	 * @param _M The maximum attribute value
	 * @param _d the dimension
	 */
	public LSLSHFunction(int _M, int _d) {
		M = _M;
		d = _d;
		r = new double[_d];
		Random rand = new Random();
		for (int i = 0; i<_d;i++ )
			r[i] = rand.nextGaussian();
		w = 4;
		b = rand.nextInt(4);
	}

	@Override
	public Integer hash(LSHRecord input) {
		double sum = 0;
		int ret;
		
		/*
		for (int i = 0 ; i < d ; i++)
			sum += r[i]*input.attributes[i];
		*/
		
		for (Entry<Integer, Double> entry : input.attributes.entrySet() )
		{
			int i = entry.getKey();
			double value = entry.getValue();
			sum += r[i]*value;
		}
		ret = (int)((sum+b)/w);
        
        if (ret < 0)
            ret += M;

		return ret % M;
	}

	
}
