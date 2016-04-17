package jmycka.storage;

import java.util.ArrayList;

public abstract class AbstracStorage<E> {

	private ArrayList<E> list = new ArrayList<E>();
	
	public void addItem(E item)
	{
		this.list.add(item);
	}
	
	public void addAllItems(ArrayList<E> list)
	{
		this.list.addAll(list);
	}
	
	public E getItem(int index)
	{
		return this.list.get(index);
	}
	
	public ArrayList<E> getItemList()
	{
		return this.list;
	}
}
