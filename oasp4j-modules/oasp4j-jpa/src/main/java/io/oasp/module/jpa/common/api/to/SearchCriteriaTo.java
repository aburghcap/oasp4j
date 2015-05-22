package io.oasp.module.jpa.common.api.to;

import net.sf.mmm.util.transferobject.api.AbstractTransferObject;

/**
 * This is the interface for a {@link net.sf.mmm.util.transferobject.api.TransferObject transfer-object } with the
 * criteria for a search and pagination query. Such object specifies the criteria selecting which hits will match when
 * performing a search.<br/>
 * <b>NOTE:</b><br/>
 * This interface only holds the necessary settings for the pagination part of a query. For your individual search, you
 * extend {@link SearchCriteriaTo} to create a java bean with all the fields for your search.
 *
 * @author hohwille
 */
public class SearchCriteriaTo extends AbstractTransferObject {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  /** @see #getPagination() */
  private PaginationTo pagination;

  /**
   * The constructor.
   */
  public SearchCriteriaTo() {

    super();
  }

  /**
   * The currently active pagination.
   *
   * @return pagination the currently active pagination or {@link PaginationTo#NO_PAGINATION} if no specific pagination
   *         has been set. Will never return {@code null}.
   */
  public PaginationTo getPagination() {

    return this.pagination == null ? PaginationTo.NO_PAGINATION : this.pagination;
  }

  /**
   * @param pagination the pagination to set
   */
  public void setPagination(PaginationTo pagination) {

    this.pagination = pagination;
  }

  /**
   * Limits the {@link PaginationTo#getSize() page size} by the given <code>limit</code>.
   * <p>
   * If currently no pagination is active, or the {@link PaginationTo#getSize() current page size} is {@code null} or
   * greater than the given {@code limit}, the value is replaced by {@code limit}
   *
   * @param limit is the maximum allowed value for the {@link PaginationTo#getSize() page size}.
   */
  public void limitMaximumPageSize(int limit) {

    if (getPagination() == PaginationTo.NO_PAGINATION) {
      setPagination(new PaginationTo());
    }

    Integer pageSize = getPagination().getSize();
    if ((pageSize == null) || (pageSize.intValue() > limit)) {
      getPagination().setSize((Integer.valueOf(limit)));
    }
  }
}
