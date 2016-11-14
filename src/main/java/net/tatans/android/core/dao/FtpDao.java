package net.tatans.android.core.dao;

import java.util.List;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.core.entity.Ftp;

public interface FtpDao {
	public List<Ftp> getList();

	public Ftp findById(Integer id);

	public Ftp save(Ftp bean);

	public Ftp updateByUpdater(Updater<Ftp> updater);

	public Ftp deleteById(Integer id);
}