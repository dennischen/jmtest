/* SearchVM.java

	Purpose:
		
	Description:
		
	History:
		2011/10/25 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
*/
package foo.jmtest.search;

import java.text.DecimalFormat;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
/**
 * a Search View Model
 * @author dennis
 *
 */
public class SearchVM {

	//the search condition
	String filter = "*";
	//the search result
	ListModelList<Item> items;
	
	//the selected item
	Item selected;
	
	
	
	protected SearchService getSearchService(){
		return new FakeSearchService();
	}
		
	public String getFilter() {
		return filter;
	}

	@NotifyChange
	public void setFilter(String filter) {
		this.filter = filter;
	}

	public ListModel<Item> getItems() {
		if(items==null){
			doSearch();
		}
		return items;
	}

	@NotifyChange({"items","selected"})
	@Command
	public void doSearch(){
		items = new ListModelList<Item>();
		items.addAll(getSearchService().search(filter));
		selected = null;
		System.out.println("do search "+filter);
	}
	
//	@NotifyChange({"items","selected","filter"})
//	public void doFixedSearch(Map<String,Object> args){
//		items = new ListModelList<Item>();
//		filter = (String)args.get("arg");
//		items.addAll(getSearchService().search(filter));
//		selected = null;
//	}

	public Item getSelected() {
		return selected;
	}

	@NotifyChange
	public void setSelected(Item selected) {
		this.selected = selected;
	}
	
	
	Converter totalPriceConverter = null;
	
	public Converter getTotalPriceConverter(){
		if(totalPriceConverter!=null){
			return totalPriceConverter;
		}
		return totalPriceConverter = new Converter(){
			public Object coerceToBean(Object val, Component component,
					BindContext ctx) {
				return null;//never called in this example
			}

			public Object coerceToUi(Object val, Component component,
					BindContext ctx) {
				if(val==null) return null;
				String str = new DecimalFormat("$ ###,###,###,##0.00").format((Double)val);
				return str;
			}
			
		};
	}
	
}
