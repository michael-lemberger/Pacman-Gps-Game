package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class GisProject implements GIS_project {
	private static Set<GIS_layer> layers=new HashSet<GIS_layer>();
	public static Set<GisLayer> setProject =new HashSet<GisLayer>();
	public boolean add(GIS_layer e) {
		setProject.add((GisLayer) e);
		return layers.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		setProject.addAll((Collection<? extends GisLayer>) c);
		return layers.addAll(c);
	}

	@Override
	public void clear() {
		setProject.clear();
		layers.clear();

	}

	@Override
	public boolean contains(Object o) {
		
		return layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		setProject.remove(o);
		return layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		setProject.removeAll(c);
		return layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		setProject.retainAll(c);
		return layers.retainAll(c);
	}

	@Override
	public int size() {
		return layers.size();
	}

	@Override
	public Object[] toArray() {
		
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return layers.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		
		return null;
	}
	public String toString() {
		int num=1;
		String send="---project---\n\n";
		for(GisLayer l : setProject) {
		send+= "layer"+num+"\n"+l+"\n ";
//			send+=""+l.size()+"\n";
			num++;
		}
		return send;
	}
	

}
