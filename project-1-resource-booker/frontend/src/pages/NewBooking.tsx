import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { api } from '../api';

type Resource = { id:string; name:string };
type Form = { resourceId:string; start:string; end:string };

export default function NewBooking(){
    const { register, handleSubmit, reset } = useForm<Form>();
    const [res, setRes] = useState<Resource[]>([]);

    useEffect(() => { api.get('/api/resources').then(r=>setRes(r.data)); }, []);

    return (
        <form className="max-w-lg mx-auto p-6 space-y-3" onSubmit={handleSubmit(async v => {
            try {
                await api.post('/api/bookings', {
                    resourceId: v.resourceId,
                    start: new Date(v.start),
                    end: new Date(v.end)
                });
                alert('Foglalás rögzítve');
                reset();
            } catch (e:any) {
                if (e?.response?.status === 409) alert('Ütköző foglalás! Válassz másik időpontot.');
                else alert('Hiba történt.');
            }
        })}>
            <h1 className="text-2xl font-bold mb-2">Új foglalás</h1>
            <select className="input" {...register('resourceId')}>
                {res.map(r=> <option key={r.id} value={r.id}>{r.name}</option>)}
            </select>
            <input className="input" type="datetime-local" {...register('start')} />
            <input className="input" type="datetime-local" {...register('end')} />
            <button className="btn">Foglalás</button>
        </form>
    );
}

