import { useEffect, useState } from 'react';
import { api } from '../api';

type Resource = { id: string; name: string; type: string; capacity?: number; description?: string };

export default function ResourcesList() {
    const [data, setData] = useState<Resource[]>([]);
    useEffect(() => {
        api.get('/api/resources').then(r => setData(r.data));
    }, []);
    return (
        <main className="mx-auto max-w-3xl p-6">
            <h1 className="text-2xl font-bold mb-4">Erőforrások</h1>
            <ul className="space-y-2">
                {data.map(r => (
                    <li key={r.id} className="p-4 rounded-xl shadow">
                        {r.name} – {r.type} {r.capacity?`(${r.capacity} fő)`:''}
                    </li>
                ))}
            </ul>
        </main>
    );
}
