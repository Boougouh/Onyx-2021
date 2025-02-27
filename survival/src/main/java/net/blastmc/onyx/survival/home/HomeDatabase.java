package net.blastmc.onyx.survival.home;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.blastmc.onyx.api.utils.Log;
import net.blastmc.onyx.survival.Main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HomeDatabase {

    private HikariDataSource source;

    public void init() throws SQLException {
        File file = new File(Main.getInstance().getDataFolder(), "Home.db");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setPoolName("home-hikari");
        config.setJdbcUrl("jdbc:sqlite:" + file);
        source = new HikariDataSource(config);
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS player_home (id INTEGER PRIMARY KEY,uuid VARCHAR(200),homename VARCHAR(200),world VARCHAR(200),x VARCHAR(200),y VARCHAR(200),z VARCHAR(200));");
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
        Log.getLogger().sendLog("§a已经连接至 SQLite！");
    }

    public Connection getConnection() {
        try {
            return this.source.getConnection();
        } catch (SQLException ex) {
            Log.getLogger().sendLog("§c无法连接至 SQLite！请尝试 MySQL！");
        }
        return null;
    }

    public void close() {
        this.source.close();
    }
}
