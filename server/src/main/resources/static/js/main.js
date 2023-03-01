const unauthenticatedDiv = document.getElementById('unauthenticated');
const authenticatedDiv = document.getElementById('authenticated');
const userNameSpan = document.getElementById('user');
// new!
const logoutButton = document.getElementById('logout');

async function getUserName() {
  const response = await fetch('/api/user/name');
  const data = await response.json();

  if (data.name) {
    authenticatedDiv.style.display = 'block';
    unauthenticatedDiv.style.display = 'none';
    userNameSpan.innerHTML = data.name;

    // new!
    logoutButton.addEventListener('click', async () => {
        const response = await fetch('/api/user/logout', { method: 'DELETE' });
        if (response.status === 204) {
            window.location = '/';
        } else {
            console.log(`Unexpected status code: ${response.status}`);
        }
    });
  }
}

getUserName();