package be.atc.services;

import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Editor;

public class EditorService {
	protected EntityManager em;

	EditorService(EntityManager em){
		this.em = em;
	}
	
	public Editor createEditor(Editor editor){
		em.persist(editor);
		
		return editor;
	}
	
	public void removeEditor(Editor editor){
		if (em.find(Editor.class, editor.getIdEditor()) != null)
			em.remove(editor);
	}
	
	public Editor modifyEditor(Editor editorTarget, Editor editorNew){
		editorTarget = em.find(Editor.class, editorNew.getIdEditor());
		
		return editorTarget;
	}
	
	public Editor findCategory(Editor editor){
		return em.find(Editor.class, editor.getIdEditor());
	}
	
	public List<Editor> findAllEditors(){
		javax.persistence.TypedQuery<Editor> query = (javax.persistence.TypedQuery<Editor>) em.createQuery("SELECT e FROM Editor e", Editor.class);
		return query.getResultList();
	}
}