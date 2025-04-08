import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
    private int indexF = 0;
    private int indexP = 0;

    private Fornecedor[] fornecedor = new Fornecedor[5];
    private Produto[] produto = new Produto [5];

    public void menuPrincipal(){
        int opcao;
        String menu = "1- Cadastrar produto \n2- Pesquisar produto \n3- Pesquisar fornecedor \n4- Finalizar";
        do{
        opcao = parseInt(showInputDialog(menu));
        if(opcao<1 || opcao>4){
            showMessageDialog(null, "opção inválida");
        }
        else{
            switch(opcao){
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    pesquisarProduto();
                    break;
                case 3:
                    pesquisar();
                    break;
                }
            }
        }while(opcao != 4);

    }

    private void pesquisar(){
        Fornecedor fornecedor = pesquisarFornecedor();
        if(fornecedor != null){
            String aux = "";
            aux += "Fornecedor: "+fornecedor.getNome()+"\n";
            aux += "CNPJ: "+fornecedor.getCnpj()+"\n";
            showMessageDialog(null, aux);
        }
    }

    private void cadastrarProduto(){
        String nome;
        double valor;
        int estoque;
        Fornecedor fornecedor = pesquisarFornecedor();

        if(fornecedor == null){
            fornecedor = cadastrarFornecedor();
        }
        nome = showInputDialog("Nome do produto");
        valor = parseDouble(showInputDialog("Valor unitário"));
        estoque = parseInt(showInputDialog("Quantidade em estoque"));
        produto[indexP] = new Produto(nome, valor, estoque, fornecedor);
        indexP++;
    }

    private Fornecedor cadastrarFornecedor(){
        String nome = showInputDialog("Nome do fornecedor: ");
        int cnpj = parseInt(showInputDialog("CNPJ: "));
        Fornecedor fornecedor = new Fornecedor(nome, cnpj);
        this.fornecedor[indexF] = fornecedor;
        indexF++;
        return fornecedor;
    }

    private void pesquisarProduto(){
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "Produto não encontrado";
        String nome=showInputDialog("Nome do produto");
        for (int i=0; i<indexP; i++){
            if(produto[i].getNome().equalsIgnoreCase(nome)){
             aux = "";
             aux += "Nome do produto: "+nome+"\n";
             aux += "Valor do produto: "+df.format(produto[i].getValor())+"\n";
             aux += "Fornecedor: "+produto[i].getFornecedor().getNome()+"\n";
            }
        }
        showMessageDialog(null, aux);
    }

    private Fornecedor pesquisarFornecedor(){
        int cnpj = parseInt(showInputDialog("CNPJ do fornecedor"));
        for(int i=0; i<indexF; i++){
            if(fornecedor[i].getCnpj() == cnpj){
                return fornecedor[i];
            }
        }
        showMessageDialog(null, "CNPJ não encontrado");
        return null;
    }


}
