package com.sda.java.training.backend.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ProduktService implements IProduktService{

  @PostConstruct
  public void initDriver() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sda", "root", "hUfKTHTjC2X99s4GTFEgUeVy");
    } catch (Exception e) {
      System.out.println("Nepodarilo se vytvorit spojeni do databaze");
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Produkt> findAll() {
    LinkedList<Produkt> result = new LinkedList<Produkt>();
    try {
      Connection con = getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from produkt");
      while (rs.next()) {
        result.add(new Produkt(rs.getInt("id"), rs.getString("nazev"), rs.getString("popis")));
      }
      con.close();
    } catch (SQLException e) {
      System.out.println("Chyba v programu, chybny SQL prikaz");
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public Produkt getById(Long id) {
    try {
      Connection con = getConnection();
      PreparedStatement stmt = con.prepareStatement("select * from produkt WHERE id = ?");
      stmt.setLong(1,id);
      ResultSet rs = stmt.executeQuery();
      Produkt result = null;
      if (rs.next()) {
         result = new Produkt(rs.getInt("id"), rs.getString("nazev"), rs.getString("popis"));
      }
      con.close();
      return result;
    } catch (SQLException e) {
      System.out.println("Chyba v programu, chybny SQL prikaz");
      throw new RuntimeException(e);
    }
  }

  @Override
  public Produkt create(Produkt produkt) {
    try {
      Connection con = getConnection();
      PreparedStatement stmt = con.prepareStatement("INSERT INTO produkt (nazev, popis) VALUES (?, ?)");
      stmt.setString(1,produkt.getNazev());
      stmt.setString(2,produkt.getPopis());
      stmt.executeUpdate();
      ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
      Integer id = null;
      if (rs.next()) {
        id = rs.getInt(1);
      }
      Produkt result = getById(id.longValue());
      con.close();
      return result;
    } catch (SQLException e) {
      System.out.println("Chyba v programu, chybny SQL prikaz");
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Produkt resource) {

  }

  @Override
  public void deleteById(Long id) {

  }

  private Connection getConnection() {
    try {
      return DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/sda", "root", "heslo");
    } catch (Exception e) {
      System.out.println("Nepodarilo se vytvorit spojeni do databaze");
      throw new RuntimeException(e);
    }
  }


}
