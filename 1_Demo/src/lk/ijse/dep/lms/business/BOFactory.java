package lk.ijse.dep.lms.business;


import lk.ijse.dep.lms.business.custom.impl.*;

public class BOFactory {

    public enum BOTypes{
        MANAGE_AUTHOR,MANAGE_AUTHOR_BOOK,MANAGE_BOOKS,MANAGE_BORROW,MANAGE_MEMBER, MANAGE_PUBLISHER,MANAGE_PUBLISHER_BOOK,MANAGE_RETURNS
    }

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case MANAGE_AUTHOR: return (T) new ManageAuthorBOImpl();
            case MANAGE_AUTHOR_BOOK: return (T) new ManageAuthor_BookBOImpl();
            case MANAGE_BOOKS: return (T) new ManageBooksBOImpl();
            case MANAGE_BORROW: return (T) new ManageBorrowBOImpl();
            case MANAGE_MEMBER: return (T) new ManageMemberBOImpl();
            case MANAGE_PUBLISHER: return (T) new ManagePublisherBOImpl();
            case MANAGE_PUBLISHER_BOOK: return (T) new ManagePublisher_BookBOImpl();
            case MANAGE_RETURNS: return (T) new ManageReturnsBOImpl();
            default:
                return null;
        }
    }

}
