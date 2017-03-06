package vlpa.java.practices;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Test {

    private Set<BigInteger> dealIds = new HashSet<>();

    public Set<BigInteger> getDealIds() {
        return dealIds;
    }

    public static Collection<BigInteger> convert(Collection<BigInteger> ids) {
        return Collections.unmodifiableCollection(ids);
    }

    public static void main(String[] args) {
        Test t = new Test();

    }

}
