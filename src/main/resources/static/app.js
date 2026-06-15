const API = 'http://localhost:8080/habits';

document.getElementById('type').addEventListener('change', function() {
    const container = document.getElementById('timesPerWeekContainer');
    container.style.display = this.value === 'weekly' ? 'block' : 'none';
});

async function loadHabits() {
    const response = await fetch(API);
    const habits = await response.json();
    const list = document.getElementById('habitList');
    list.innerHTML = '';

    if (habits.length === 0) {
        list.innerHTML = `
            <div class="empty-state">
                <p>—</p>
                <p>No tienes hábitos aún.<br>Crea uno para empezar.</p>
            </div>`;
        return;
    }

    habits.forEach((habit, index) => {
        const card = document.createElement('div');
        card.className = 'habit-card ' + (habit.completedForCurrentPeriod ? 'completed' : 'pending');
        card.innerHTML = `
            <div class="habit-info">
                <h3>${habit.name}</h3>
                <p>
                    <span class="habit-badge">${habit.category}</span>
                    <span class="habit-badge">${habit.frequency}</span>
                    ${habit.description ? habit.description + ' · ' : ''}
                    Racha: ${habit.currentStreak}
                    ${habit.completedForCurrentPeriod ? ' · Completado' : ''}
                </p>
            </div>
            <div class="habit-actions">
                <button class="btn-action btn-complete" onclick="complete(${index + 1})">Completar</button>
                <button class="btn-action btn-delete" onclick="deleteHabit(${index + 1})">Eliminar</button>
            </div>
        `;
        list.appendChild(card);
    });
}

async function createHabit() {
    const type = document.getElementById('type').value;
    const body = {
        name: document.getElementById('name').value,
        description: document.getElementById('description').value,
        category: document.getElementById('category').value,
        timesPerWeek: type === 'weekly' ? parseInt(document.getElementById('timesPerWeek').value) : 0
    };

    await fetch(API, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });

    document.getElementById('name').value = '';
    document.getElementById('description').value = '';
    loadHabits();
}

async function complete(index) {
    await fetch(`${API}/${index}/complete`, { method: 'POST' });
    loadHabits();
}

async function deleteHabit(index) {
    await fetch(`${API}/${index}`, { method: 'DELETE' });
    loadHabits();
}

loadHabits();