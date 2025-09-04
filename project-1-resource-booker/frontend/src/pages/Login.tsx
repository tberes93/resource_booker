import { useForm } from 'react-hook-form';
import { api } from '../api';
import { useNavigate } from 'react-router-dom';
import {useAuth} from "../auth/AuthContext.tsx";

type Form = { email: string; password: string };

export default function Login(){
    const { register, handleSubmit } = useForm<Form>();
    const nav = useNavigate();
    const { login } = useAuth();

    return (
        <form className="max-w-sm mx-auto p-6 space-y-3" onSubmit={handleSubmit(async v => {
            const res = await api.post('/api/auth/login', v);
            login( res.data.accessToken);

            nav('/');
        })}>
            <h1 className="text-2xl font-bold mb-4">Belépés</h1>
            <input className="input" placeholder="Email" {...register('email')} />
            <input className="input" type="password" placeholder="Jelszó" {...register('password')} />
            <button className="btn">Belépés</button>
        </form>
    );
}

