package bandas;

import aula13.ConexaoSQLite;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Bandas {
    private ArrayList listaBandas;
    private JTable tabela;
    private DefaultTableModel tabela_model;
    
    public Bandas() {
        this.listaBandas = new ArrayList();
        this.tabela = null;
        this.tabela_model = null;
    }

    public Bandas(JTable tabela) {
        this.listaBandas = new ArrayList();
        this.tabela = tabela;
        this.criaModelo();
    }
    
    public void criaModelo() {
        this.tabela_model = new DefaultTableModel(
            new String [][] {},
            new String [] {
                "CODIGO", "NOME", "ESTILO", "PAIS"
            }
        );
        tabela.setModel(this.tabela_model);
    }
            
    public void atualiza() {
        
        if(this.tabela != null) {
            this.criaModelo();            
        }
        
        ConexaoSQLite conexao = new ConexaoSQLite("bancoAula13.sqlite");
        conexao.query("SELECT * FROM tb_bandas");
        
        while(conexao.next()) {
            int codigo = conexao.getInt("codigo");
            String nome = conexao.getString("nome");
            String estilo = conexao.getString("estilo");
            String pais = conexao.getString("pais");
            Banda b = new Banda(codigo,nome,estilo,pais);
            this.listaBandas.add(b);
            if(this.tabela != null) {
                this.tabela_model.addRow(new String [] {
                    ""+codigo, nome, estilo, pais
                });                
            }
        }
    }
    
    public void imprime() {
        Iterator it = this.listaBandas.iterator();
        while(it.hasNext()) {
            Banda b = (Banda) it.next();
            b.imprime();
        }
    }
}
