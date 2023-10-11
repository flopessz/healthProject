package musicas;

import bandas.*;
import aula13.ConexaoSQLite;

public class Musica {
    private int codigo;
    private String nome;
    private String estilo;
    private String pais;
    
    public Musica(int codigo) {
        ConexaoSQLite conexao = new ConexaoSQLite("bancoAula13.sqlite");
        String query = "SELECT * FROM tb_bandas WHERE codigo = "+codigo;
        conexao.query(query);
        if(conexao.next()) {
            this.codigo = codigo;
            this.nome = conexao.getString("nome");
            this.estilo = conexao.getString("estilo");
            this.pais = conexao.getString("pais");
        } else {
            System.out.println("Banda não encontrada!");
        }
        conexao.close();
    }
    
    public Musica(int codigo, String nome, String estilo, String pais) {
        this.codigo = codigo;
        this.nome = nome;
        this.estilo = estilo;
        this.pais = pais;
    }

    public void insere() {        
        ConexaoSQLite conexao = new ConexaoSQLite("bancoAula13.sqlite");
        String query = "INSERT INTO tb_bandas (nome,estilo,pais) VALUES (";
        query = query + "'" + this.getNome() + "',";
        query = query + "'" + this.getEstilo() + "',";
        query = query + "'" + this.getPais() + "'";
        query = query + ")";
        System.out.println(query);
        conexao.queryUpdate(query);
        conexao.close();
    }

    public void atualizar() {        
        ConexaoSQLite conexao = new ConexaoSQLite("bancoAula13.sqlite");
        String query = "UPDATE tb_bandas set ";
        query = query + "nome = '" + this.getNome() + "',";
        query = query + "estilo = '" + this.getEstilo() + "',";
        query = query + "pais = '" + this.getPais() + "'";
        query = query + "WHERE codigo = "+this.codigo;
        System.out.println(query);
        conexao.queryUpdate(query);
        conexao.close();
    }
    
    public void imprime() {
        System.out.print("BANDA ["+this.getCodigo()+"]");
        System.out.print(" nome: "+this.getNome());
        System.out.print(", estilo: "+this.getEstilo());
        System.out.print(", pais: "+this.getPais());
        System.out.println("");
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the estilo
     */
    public String getEstilo() {
        return estilo;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }
    
}
