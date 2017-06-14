package br.cefetrj.webdep.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.transform.ToListResultTransformer;

import java.util.Set;

import br.cefetrj.webdep.model.entity.RegistroLogErro;

/**
 * 
 * @author Rafael
 * @since 30/05/2017
 * 
 * Classe criado para intuito de realizar testes, totalmente inutilizavel
 *
 */
public class Teste {
	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		lista.add("PHP Notice: Undefined offset: 6 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144");
		lista.add("PHP Notice: Undefined offset: 7 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144");
		lista.add("PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancamento_funcionario.php on line 215");
		lista.add("PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancamento_funcionario.php on line 215");
		lista.add("PHP Warning: Division by zero in /var/www/html/avaliacao-dicap/modeloEP.php on line 57");
		lista.add("PHP Warning: Division by zero in /var/www/html/avaliacao-dicap/modeloEP.php on line 58");
		lista.add("PHP Notice: Undefined index: nota_chefia_1 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 89");
		lista.add("PHP Notice: Undefined index: nota_auto_1 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 90");
		lista.add("PHP Notice: Undefined index: ano in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 25");
		lista.add("PHP Notice: Undefined index: ano in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 25");
		lista.add("PHP Notice: Undefined index: next in /var/www/html/avaliacao-dicap/login_page.php on line 93");
		lista.add("PHP Notice: Undefined index: next in /var/www/html/avaliacao-dicap/login_page.php on line 93");
		lista.add("PHP Warning: Invalid argument supplied for foreach() in /var/www/html/avaliacao-dicap/lancar_recebimento.php on line 90");
		lista.add("PHP Notice: Undefined offset: 8 in /var/www/html/avaliacao-dicap/modeloADF.php on line 144");
		lista.add("PHP Notice: Undefined index: fv_3 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 91");
		lista.add("PHP Notice: Undefined index: fv_3 in /var/www/html/avaliacao-dicap/lancamento_funcionario_post.php on line 91");
		
		List<Defeito> listaDefeito = new ArrayList<>();

		List<Defeito> retorno = new ArrayList<>();
		
		Map<String, IntSummaryStatistics> teste = new HashMap<>();
		
		for (String linha: lista) {
			String aux[] = linha.split("\\w{5}/");
			aux = aux[1].split(" on line ");
			listaDefeito.add(new Defeito(aux[0], aux[0], Integer.parseInt(aux[1]), 1l));
//			System.out.print("Nome: ");
//			System.out.println(aux[0]);
//			System.out.print("Linha: ");
//			System.out.println(aux[1]);
		}
		
//		listaDefeito.parallelStream().collect(Collectors.groupingBy(Defeito::getPrimaryKey, Collectors.toList()));

		teste = listaDefeito.stream().collect(Collectors.groupingBy(Defeito::getPrimaryKey, Collectors.summarizingInt(Defeito::getNumFalha)));
		
//		teste.entrySet().forEach(System.out::println);
		
		for(Entry<String, IntSummaryStatistics> aux: teste.entrySet()) {
			String parse[] = aux.getKey().split(" ");
			retorno.add(new Defeito(parse[0], parse[0], Integer.parseInt(parse[1]), aux.getValue().getSum()));
		}
		
		retorno.forEach(System.out::println);
		
//		Iterator<Defeito> it = listaDefeito.iterator();
//		while(it.hasNext()){
//			Defeito dUm = it.next();
//			for (int i = 1; i < listaDefeito.size(); i++) {
//				Defeito dDois = listaDefeito.get(i);
//				if (dUm.equals(dDois)){
//					dUm.setNumFalha(dUm.getNumFalha() + 1);
//					retorno.add(dUm);
//				}
//			}
//		}
//		
//		for(Defeito d: retorno) {
//			teste.put(d.getArquivo(), d);
//		}
//		retorno = new ArrayList<>();
//		for (Entry<String, Defeito> d: teste.entrySet()) {
//			retorno.add(d.getValue());
//		}
//
//		retorno.forEach(System.out::println);
//		32/13/2010

	}
}
;