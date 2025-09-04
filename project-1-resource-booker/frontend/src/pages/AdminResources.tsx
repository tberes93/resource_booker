import { useEffect, useState } from 'react';
import { api } from '../api';

type Resource = { id?:string; name:string; type:string; capacity?:number; description?:string };

export default function AdminResources(){
    const [items, setItems] = useState<Resource[]>([]);
    const [form, setForm] = useState<Resource>({ name:'', type:'ROOM', capacity:0, description:'' });

    const load = async()=> setItems((await api.get('/api/resources')).data);
    useEffect(()=>{ load(); },[]);

    const save = async()=>{
        if (form.id) await api.put(`/api/resources/${form.id}`, form);
        else await api.post('/api/resources', form);
        setForm({ name:'', type:'ROOM', capacity:0, description:'' });
        await load();
    };

    const edit = (r:Resource)=> setForm(r);
    const del = async(id?:string)=>{ if(!id) return; await api.delete(`/api/resources/${id}`); await load(); };

    return (
        <div className="mx-auto max-w-4xl p-6 space-y-6">
            <h1 className="text-2xl font-bold">Admin – Erőforrások</h1>
            <div className="p-4 rounded-xl shadow grid gap-3 md:grid-cols-4">
                <input className="input" placeholder="Név" value={form.name} onChange={e=>setForm({...form,name:e.target.value})} />
                <input className="input" placeholder="Típus" value={form.type} onChange={e=>setForm({...form,type:e.target.value})} />
                <input className="input" type="number" placeholder="Kapacitás" value={form.capacity??0} onChange={e=>setForm({...form,capacity:+e.target.value})} />
                <button className="btn" onClick={save}>{form.id? 'Mentés' : 'Új erőforrás'}</button>
            </div>
            <ul className="space-y-2">
                {items.map(r=> (
                    <li key={r.id} className="p-4 rounded-xl shadow flex items-center justify-between">
                        <span>{r.name} – {r.type} {r.capacity?`(${r.capacity})`:''}</span>
                        <div className="space-x-2">
                            <button className="btn" onClick={()=>edit(r)}>Szerkeszt</button>
                            <button className="btn" onClick={()=>del(r.id)}>Törlés</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}
