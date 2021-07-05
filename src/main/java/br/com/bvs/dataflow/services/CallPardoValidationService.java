package br.com.bvs.dataflow.services;

import org.apache.beam.sdk.io.FileIO.ReadableFile;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.ParDo.SingleOutput;

public class CallPardoValidationService {
	
	
	public SingleOutput<ReadableFile, String> callPardoValidation(){
		
		
			return ParDo.of(new ValidadeClienteService());
		
			
		
	}

}
