/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.bvs.dataflow;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bvs.dataflow.model.Cliente;
import br.com.bvs.dataflow.services.CallPardoValidationService;

/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class StarterPipeline {
  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

  public static void main(String[] args) {
	  
    
	  Pipeline primeira = Pipeline.create();
	  
	  primeira.apply("STARTING FILEIO",FileIO.match().filepattern("gs://monitoramento-risco-1/entrada/entrada.txt")).apply(FileIO.readMatches())
		.apply("STARTING LAYOUT VALIDATION", new CallPardoValidationService().callPardoValidation())
		.apply("SAVING FILE ON MEMORY",
				TextIO.write().withoutSharding().to("C:\\SA\\saida.txt"))
		.getPipeline();
	  primeira.run().waitUntilFinish();
	  
	  
	  primeira.run();
  }
}
