package net.tatans.android.core.dao;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.core.entity.DbFile;

public interface DbFileDao {
	public DbFile findById(String id);

	public DbFile save(DbFile bean);

	public DbFile updateByUpdater(Updater<DbFile> updater);

	public DbFile deleteById(String id);
}