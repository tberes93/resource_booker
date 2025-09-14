import {Routes, Route, Link, Navigate, useNavigate} from 'react-router-dom';
import ResourcesList from './pages/ResourcesList';
import Login from './pages/Login';
import NewBooking from './pages/NewBooking';
import AdminResources from './pages/AdminResources';
import ProtectedRoute from './routes/ProtectedRoute';
import AdminRoute from './routes/AdminRoute';
import Registration from "./pages/Registration.tsx";
import {useAuth} from "./auth/AuthContext.tsx";

export default function App() {
    const {authed, role, logout} = useAuth();
    const nav = useNavigate();
    return (
        <div>
            <nav className="px-6 py-4 shadow flex gap-4 items-center">
                <button><Link to="/" className="font-bold">Erőforrások listája</Link></button>
                {authed ? <button><Link to="/new-booking">Erőforrás foglalás</Link></button> : ""}
                {role === 'ADMIN' && <button><Link to="/admin/resources">Erőforrások kezelése (admin)</Link></button>}
                {authed
                    ? <button className="btn" onClick={() => {
                        logout();
                        nav('/login');
                    }}>Kilépés</button>
                    : <button><Link className="btn" to="/login">Belépés</Link></button>}
                {!authed
                    ? <button className="btn" onClick={() => {
                        logout();
                        nav('/registration');
                    }}>Regisztráció</button>
                    : ""}
            </nav>
            <div className="p-6">
                <Routes>
                    <Route path="/" element={<ResourcesList/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/registration" element={<Registration/>}/>
                    <Route path="/new-booking" element={<ProtectedRoute><NewBooking/></
                        ProtectedRoute>}/>
                    <Route path="/admin/resources"
                           element={<AdminRoute><AdminResources/></AdminRoute>}/>
                    <Route path="*" element={<Navigate to="/"/>}/>
                </Routes>
            </div>
        </div>
    );
}


