package lk.ijse.dep.lms.dao;


import lk.ijse.dep.lms.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public enum DAOTypes{
        AUTHOR,AUTHOR_BOOK,BOOKS,BORROW,MEMBER, PUBLISHER,PUBLISHER_BOOK,RETURNS,QUERY
    }

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case AUTHOR: return (T) new AuthorDAOImpl();
            case AUTHOR_BOOK: return (T) new Author_BookDAOImpl();
            case BOOKS: return (T) new BooksDAOImpl();
            case BORROW: return (T) new BorrowDAOImpl();
            case MEMBER: return (T) new MemberDAOImpl();
            case PUBLISHER: return (T) new PublisherDAOImpl();
            case PUBLISHER_BOOK: return (T) new Publisher_BookDAOImpl();
            case RETURNS: return (T) new ReturnsDAOImpl();
            case QUERY: return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
