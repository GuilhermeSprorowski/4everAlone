package com.ufpr.tads.dac.dao.impl;

import com.ufpr.tads.dac.beans.CidadeBean;
import com.ufpr.tads.dac.beans.ClienteBean;
import com.ufpr.tads.dac.beans.CorCabeloBean;
import com.ufpr.tads.dac.beans.CorPeleBean;
import com.ufpr.tads.dac.beans.EnderecoBean;
import com.ufpr.tads.dac.beans.EscolaridadeBean;
import com.ufpr.tads.dac.beans.EstadoBean;
import com.ufpr.tads.dac.beans.PreferenciaBean;
import com.ufpr.tads.dac.dao.ClienteDAO;
import com.ufpr.tads.dac.dao.ConnectionFactory;
import com.ufpr.tads.dac.exceptions.ClienteException;
import com.ufpr.tads.dac.exceptions.EnderecoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAOimpl implements ClienteDAO {

    private Connection con;

    @Override
    public void setCliente(ClienteBean c, String email) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int idGerado = 0;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("INSERT INTO bd4everalone.usuario(email)VALUES(?);", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, email);
            int resp = pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            while (rs.next()) {
                idGerado = rs.getInt(1);
            }
            resp = 0;
            if (idGerado == 0) {
                throw new ClienteException("Erro cliente: não foi possivel gerar esse login");
            } else {
                pst = con.prepareStatement("INSERT INTO bd4everalone.cliente(nome, cpf, sexo) VALUES(?,?,?);");
                pst.setString(1, c.getNome());
                pst.setString(2, c.getCpf());
                pst.setString(3, c.getSexo());
                resp = pst.executeUpdate();
                if (resp == 0) {
                    throw new ClienteException("Erro cliente: não foi possivel criar esse cliente");
                }
            }
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public void updateCliente(ClienteBean c) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            int idEndereco;
            if (c.getEndereco().getEnderecoId() == 0) {
                con = new ConnectionFactory().getConnection();
                pst = con.prepareStatement("INSERT INTO bd4everalone.endereco(codCidade, rua) VALUE(?,?);", Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, c.getEndereco().getCidade().getIdCidade());
                pst.setString(2, c.getEndereco().getRua());
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    idEndereco = rs.getInt(1);
                } else {
                    throw new ClienteException("Erro cliente: não foi possivel salvar as informações do endereço do cliente.");
                }
            } else {
                con = new ConnectionFactory().getConnection();
                pst = con.prepareStatement("UPDATE bd4everalone.endereco SET codCidade = ?, rua = ? WHERE id = ?;");
                pst.setInt(1, c.getEndereco().getCidade().getIdCidade());
                pst.setString(2, c.getEndereco().getRua());
                pst.setInt(3, c.getEndereco().getEnderecoId());
                int resp = pst.executeUpdate();
                idEndereco = c.getEndereco().getEnderecoId();
                if (resp == 0) {
                    throw new ClienteException("Erro cliente: não foi possivel salvar as informações do endereço do cliente.");
                }
            }
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("UPDATE bd4everalone.cliente SET descricao = ?, codEscolaridade= ?, codPele = ?, codCabelo = ?, codEndereco = ?, altura=? WHERE id = ?;");
            pst.setString(1, c.getDescricao());
            pst.setInt(2, c.getEscolaridade().getIdEscolaridade());
            pst.setInt(3, c.getCorPele().getIdCorPele());
            pst.setInt(4, c.getCorCabelo().getIdCorCabelo());
            pst.setInt(5, idEndereco);
            pst.setInt(6, c.getAltura());
            pst.setInt(7, c.getClienteId());
            int resp = pst.executeUpdate();
            if (resp == 0) {
                throw new ClienteException("Erro cliente: não foi possivel salvar as informações do cliente.");
            }
            if (c.getPreferencias().getIdPreferencia() == 0) {
                con = new ConnectionFactory().getConnection();
                pst = con.prepareStatement("INSERT INTO bd4everalone.preferencia(sexo, idadeMin, idadeMax,alturaMin,alturaMax,codPele, codCabelo, codCliente)VALUE(?,?,?,?,?,?,?,?);");
                PreferenciaBean pr = c.getPreferencias();
                pst.setString(1, pr.getSexo());
                pst.setInt(2, pr.getIdade()[0]);
                pst.setInt(3, pr.getIdade()[1]);
                pst.setInt(4, pr.getAltura()[0]);
                pst.setInt(5, pr.getAltura()[1]);
                pst.setInt(6, pr.getCorPele().getIdCorPele());
                pst.setInt(7, pr.getCorCabelo().getIdCorCabelo());
                pst.setInt(8, c.getClienteId());
                resp = pst.executeUpdate();
                System.out.println(pst);
                if (resp == 0) {
                    throw new ClienteException("Erro preferencia: não foi possivel salvar as informações das preferencias do cliente.");
                }
            } else {
                con = new ConnectionFactory().getConnection();
                pst = con.prepareStatement("UPDATE bd4everalone.preferencia SET sexo = ?, idadeMin = ?, idadeMax = ?, "
                        + "alturaMin = ?, alturaMax = ?, codPele = ?, codCabelo = ?, codCliente = ? WHERE id = ?;");
                PreferenciaBean pr = c.getPreferencias();
                pst.setString(1, pr.getSexo());
                pst.setInt(2, pr.getIdade()[0]);
                pst.setInt(3, pr.getIdade()[1]);
                pst.setInt(4, pr.getAltura()[0]);
                pst.setInt(5, pr.getAltura()[1]);
                pst.setInt(6, pr.getCorPele().getIdCorPele());
                pst.setInt(7, pr.getCorCabelo().getIdCorCabelo());
                pst.setInt(8, c.getClienteId());
                pst.setInt(9, pr.getIdPreferencia());
                resp = pst.executeUpdate();
                System.out.println(pst);
                if (resp == 0) {
                    throw new ClienteException("Erro preferencia: não foi possivel salvar as informações das preferencias do cliente.");
                }
            }
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public ClienteBean getClienteById(int clienteId) throws ClienteException, EnderecoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT Cli.id as id, Cli.nome, cpf, dataNasc, Cli.sexo as sexoc, Cli.descricao as descricao,altura, codEscolaridade,  E.descricao as escolaridade,  \n"
                    + "c.descricao as corCabelo, c.id as idCabelo, estado.id as codEstado, p.descricao as corPele,p.id as idPele,pref.id as codPreferencia, pref.*,codEndereco,ende.*,cidade.nome as cidade, estado.sigla as uf,\n"
                    + "(SELECT descricao FROM corPele WHERE pref.codPele = id) as prefCorPele, (SELECT descricao FROM corCabelo WHERE pref.codCabelo = id) as prefCorCabelo\n"
                    + "FROM bd4everalone.cliente Cli\n"
                    + "LEFT JOIN bd4everalone.escolaridade E ON codEscolaridade = E.id\n"
                    + "LEFT JOIN bd4everalone.corcabelo c ON Cli.codCabelo = c.id\n"
                    + "LEFT JOIN bd4everalone.corpele p ON Cli.codPele = p.id\n"
                    + "LEFT JOIN bd4everalone.preferencia pref ON Cli.id = codCliente\n"
                    + "LEFT JOIN bd4everalone.endereco ende ON ende.id = codEndereco\n"
                    + "LEFT JOIN bd4everalone.cidade on codCidade = cidade.id\n"
                    + "LEFT JOIN bd4everalone.estado on codEstado = estado.id\n"
                    + "WHERE Cli.id = ?;");
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();
            while (rs.next()) {
                CorPeleBean cp = new CorPeleBean(rs.getInt("idPele"), rs.getString("corPele"));
                CorCabeloBean cc = new CorCabeloBean(rs.getInt("idCabelo"), rs.getString("corCabelo"));
                EnderecoBean eb = new EnderecoBean(rs.getInt("codEndereco"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")), new EstadoBean(rs.getInt("codEstado"), rs.getString("uf")));
                EscolaridadeBean esco = new EscolaridadeBean(rs.getInt("codEscolaridade"), rs.getString("escolaridade"));

                int[] pa = {rs.getInt("alturaMin"), rs.getInt("alturaMax")};
                int[] pi = {rs.getInt("idadeMin"), rs.getInt("idadeMax")};
                PreferenciaBean pb = new PreferenciaBean(rs.getInt("codPreferencia"), rs.getString("sexo"), pi, pa, new CorCabeloBean(rs.getInt("codCabelo"), rs.getString("prefCorCabelo")), new CorPeleBean(rs.getInt("codPele"), rs.getString("prefCorPele")));
                return new ClienteBean(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getDate("dataNasc"), rs.getString("sexoc"),
                        rs.getString("descricao"), cp, cc, eb, esco, pb, rs.getInt("altura"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<ClienteBean> getAllClientes() throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ClienteBean> cl = new ArrayList<>();
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT DISTINCT Cli.id as id, Cli.nome, cpf, dataNasc, Cli.sexo as sexoc, Cli.descricao as descricao,altura, codEscolaridade,  E.descricao as escolaridade,  \n"
                    + "c.descricao as corCabelo, c.id as idCabelo, estado.id as codEstado, p.descricao as corPele,p.id as idPele,pref.id as codPreferencia, pref.*,codEndereco,ende.*,cidade.nome as cidade, estado.sigla as uf,\n"
                    + "(SELECT descricao FROM corPele WHERE pref.codPele = id) as prefCorPele, (SELECT descricao FROM corCabelo WHERE pref.codCabelo = id) as prefCorCabelo, usu.email as email\n"
                    + "FROM bd4everalone.cliente Cli\n"
                    + "LEFT JOIN bd4everalone.escolaridade E ON codEscolaridade = E.id\n"
                    + "LEFT JOIN bd4everalone.corcabelo c ON Cli.codCabelo = c.id\n"
                    + "LEFT JOIN bd4everalone.corpele p ON Cli.codPele = p.id\n"
                    + "LEFT JOIN bd4everalone.preferencia pref ON Cli.id = codCliente\n"
                    + "LEFT JOIN bd4everalone.endereco ende ON ende.id = codEndereco\n"
                    + "LEFT JOIN bd4everalone.cidade on codCidade = cidade.id\n"
                    + "LEFT JOIN bd4everalone.usuario usu ON usu.id = Cli.codUser\n"
                    + "LEFT JOIN bd4everalone.estado on codEstado = estado.id");
            rs = pst.executeQuery();
            while (rs.next()) {
                CorPeleBean cp = new CorPeleBean(rs.getInt("idPele"), rs.getString("corPele"));
                CorCabeloBean cc = new CorCabeloBean(rs.getInt("idCabelo"), rs.getString("corCabelo"));
                EnderecoBean eb = new EnderecoBean(rs.getInt("codEndereco"), rs.getString("rua"), new CidadeBean(rs.getInt("codCidade"), rs.getString("cidade")), new EstadoBean(rs.getInt("codEstado"), rs.getString("uf")));
                EscolaridadeBean esco = new EscolaridadeBean(rs.getInt("codEscolaridade"), rs.getString("escolaridade"));

                int[] pa = {rs.getInt("alturaMin"), rs.getInt("alturaMax")};
                int[] pi = {rs.getInt("idadeMin"), rs.getInt("idadeMax")};
                PreferenciaBean pb = new PreferenciaBean(rs.getInt("codPreferencia"), rs.getString("sexo"), pi, pa, new CorCabeloBean(rs.getInt("codCabelo"), rs.getString("prefCorCabelo")), new CorPeleBean(rs.getInt("codPele"), rs.getString("prefCorPele")));
                cl.add(new ClienteBean(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getDate("dataNasc"), rs.getString("sexoc"),
                        rs.getString("descricao"), cp, cc, eb, esco, pb, rs.getInt("altura"), rs.getString("email")));
            }
            return cl;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public boolean isCpfDisponivel(String cpf) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT false as cpfValido FROM bd4everalone.cliente WHERE cpf = ?");
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("cpfValido");
            }
            return true;
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

    @Override
    public ArrayList<ClienteBean> getClientesCompativeis(PreferenciaBean p, int cidade, int idCliente) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT cliente.id, nome, cliente.descricao, sexo,  dataNasc, altura, codPele, corpele.descricao AS corpele,\n"
                    + " codCabelo, corcabelo.descricao as corcabelo, cliente.codEscolaridade, esco.descricao as escolaridade, encontro.*\n"
                    + " FROM bd4everalone.cliente \n"
                    + " INNER JOIN bd4everalone.corpele ON codPele = corpele.id\n"
                    + " INNER JOIN bd4everalone.corcabelo ON codCabelo = corcabelo.id\n"
                    + " INNER JOIN bd4everalone.endereco ON codEndereco = endereco.id\n"
                    + " INNER JOIN bd4everalone.escolaridade esco ON cliente.codEscolaridade = esco.id \n"
                    + " LEFT JOIN bd4everalone.encontro ON codCSolicitado AND codCSolicitado = cliente.id\n"
                    + "  WHERE ((altura BETWEEN ? AND ?) AND (YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(dataNasc))) BETWEEN ? AND ?)) AND sexo = ? AND codCidade = ? AND cliente.id != ? AND encontro.id IS NULL\n"
                    + " ORDER BY  codPele = ? desc, codCabelo = ? desc;");
            pst.setInt(1, p.getAltura()[0]);
            pst.setInt(2, p.getAltura()[1]);
            pst.setInt(3, p.getIdade()[0]);
            pst.setInt(4, p.getIdade()[1]);
            pst.setString(5, p.getSexo());
            pst.setInt(6, cidade);
            pst.setInt(7, idCliente);
            pst.setInt(8, p.getCorCabelo().getIdCorCabelo());
            pst.setInt(9, p.getCorPele().getIdCorPele());
            rs = pst.executeQuery();
            final ArrayList<ClienteBean> al = new ArrayList<ClienteBean>();
            while (rs.next()) {
                al.add(new ClienteBean(rs.getInt("id"), rs.getString("nome"), rs.getString("sexo"), rs.getDate("dataNasc"), rs.getInt("altura"),
                        new CorPeleBean(rs.getInt("codPele"), rs.getString("corpele")),
                        new CorCabeloBean(rs.getInt("codCabelo"), rs.getString("corcabelo")),
                        new EscolaridadeBean(rs.getInt("codEscolaridade"), rs.getString("escolaridade")))
                );
            }
            return al;
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

    public String getEmailCliente(int cliId) throws ClienteException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String ret = "";
        try {
            con = new ConnectionFactory().getConnection();
            pst = con.prepareStatement("SELECT usu.email as email FROM bd4everalone.cliente f\n"
                    + "LEFT JOIN bd4everalone.usuario usu ON f.codUser = usu.id\n"
                    + "WHERE usu.id = ?");
            pst.setInt(1, cliId);
            rs = pst.executeQuery();
            while (rs.next()) {
                ret = rs.getString("email");
            }
            return ret;
        } catch (SQLException e) {
            throw new ClienteException("Erro cliente: comando sql invalido");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    throw new ClienteException("Erro cliente: erro ao fechar conecxão");
                }
            }
        }
    }

}
