// ResourceList.tsx
import { useEffect, useState } from "react";
import { api } from "../api";

type Resource = { id: string; name: string; type: string; description: string };

export default function ResourceList() {
    const [resources, setResources] = useState<Resource[]>([]);

    useEffect(() => {
        api.get("/api/resources").then(res => setResources(res.data));
    }, []);

    return (
        <div>
            <h1 style={{ marginBottom: "16px" }}>Elérhető erőforrások</h1>
            <div className="book-list">
                {resources.map(r => (
                    <div className="book-card" key={r.id}>
                        <div>
                            <h2>{r.name}</h2>
                            <p><strong>Típus:</strong> {r.type}</p>
                            <p>{r.description}</p>
                        </div>
                        <button onClick={() => alert(`Foglalás indítása: ${r.name}`)}>
                            Foglalás
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}
