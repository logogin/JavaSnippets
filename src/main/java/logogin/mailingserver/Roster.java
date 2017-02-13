import javax.mail.Address;


/**
 *
 * @created Jun 16, 2011
 * @author Pavel Danchenko
 */
public interface Roster {

    boolean containsOneOf(Address[] from);

    Address[] getAddresses();

}
