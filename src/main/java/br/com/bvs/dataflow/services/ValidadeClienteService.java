package br.com.bvs.dataflow.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.beam.sdk.io.FileIO.ReadableFile;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bvs.dataflow.model.Cliente;

public class ValidadeClienteService extends DoFn<ReadableFile, String> {

	private static final long serialVersionUID = 3542070401946976272L;

	private static final Logger log = LoggerFactory.getLogger(ValidadeClienteService.class);

	@ProcessElement
	public void processElement(ProcessContext c) throws IOException {
		log.info("INICIANDO O PROCESSO DE LEITURA DO ARQUIVO XXXX");

		Cliente cliente  =  new Cliente();
		
		ReadableFile file = c.element();

		List<String> listaNomes = new ArrayList<>();

		InputStream inputStream = Channels.newInputStream(file.open());

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		listaNomes.addAll(bufferedReader.lines().collect(Collectors.toList()));
		
		listaNomes.stream().forEach(linha ->{
         linha = validaLinha(linha);
         
         cliente.setNome(linha);
         
         c.output(cliente.getNome());
		});
		

	}
	
	
	private String validaLinha(String linha) {
		
		
		String retorno = linha;
		
		retorno = StringUtils.replace(linha.substring(0 , 7), linha, linha);
		
		
		return retorno;
	}

}
