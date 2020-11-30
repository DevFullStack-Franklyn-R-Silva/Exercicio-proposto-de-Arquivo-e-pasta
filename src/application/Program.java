package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Produto;

public class Program {

	public static void main(String[] args) {

		List<Produto> lista = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Localize o arquivo para converter para .csv: ");
			String localizar = sc.next();

			System.out.println("Criar uma pasta com nome: ");
			String nomeDaPasta = sc.next();
			System.out.println("Criar uma arquivo com nome: ");
			String nomeDoArquivo = sc.next();
			System.out.println("Que local quer salvar o arquivo? ");
			String salvar = sc.next() + nomeDaPasta + "\\" + nomeDoArquivo + ".csv";

			// c:\\temp\\Source.txt
			String caminho = "c:\\temp\\";

			try (BufferedReader br = new BufferedReader(new FileReader(localizar))) {

				String lerLinha = br.readLine();

				while (lerLinha != null) {
					String[] separar = lerLinha.split(",");

					String nome = separar[0];
					double preco = Double.parseDouble(separar[1]);
					int quantidade = Integer.parseInt(separar[2]);

					lista.add(new Produto(nome, preco, quantidade));

					lerLinha = br.readLine();

				}
				boolean sucesso = new File(caminho + nomeDaPasta).mkdir();
				System.out.println("Diretório criado com sucesso: " + sucesso);

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(salvar))) {

					for (Produto item : lista) {
						bw.write(item.getNome() + ", " + String.format("%.2f", item.total()));
						bw.newLine();
					}

					System.out.println("Criado!");
				} catch (IOException e) {
					System.out.println("Error ao escrever de arquivo: " + e.getMessage());
				}

			} catch (IOException e) {
				System.out.println("Error ao leitura de arquivo: " + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (sc != null) {
				System.out.println("Fechou");
				sc.close();
			}
		}

	}

}
