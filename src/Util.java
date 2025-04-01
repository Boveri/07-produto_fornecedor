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
                    pesquisarFornecedor();
                    break;
                }
            }
        }while(opcao != 4);

    }

    private void cadastrarProduto(){
        String nome;
        double valor;
        int estoque;
        Fornecedor fornecedor = pesquisarFornecedor();

        if(fornecedor == null){
            cadastrarFornecedor();
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

    private int pesquisarProduto(){}

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
