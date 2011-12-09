/* SearchService.java

	Purpose:
		
	Description:
		
	History:
		2011/10/25 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package foo.jmtest.search;

import java.util.List;

/**
 * @author dennis
 *
 */
public interface SearchService {
	public List<Item> search(String fitler);
}
