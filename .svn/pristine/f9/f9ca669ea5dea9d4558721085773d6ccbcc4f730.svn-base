package net.tatans.android.core.dao;

import java.util.Date;

import net.tatans.android.common.hibernate3.Updater;
import net.tatans.android.common.page.Pagination;
import net.tatans.android.core.entity.Authentication;

public interface AuthenticationDao {

	public int deleteExpire(Date d);

	public Authentication getByUserId(Long userId);

	public Pagination getPage(int pageNo, int pageSize);

	public Authentication findById(String id);

	public Authentication save(Authentication bean);

	public Authentication updateByUpdater(Updater<Authentication> updater);

	public Authentication deleteById(String id);
}