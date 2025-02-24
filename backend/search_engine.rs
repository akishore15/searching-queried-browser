use reqwest::blocking::Client;
use serde::Deserialize;

#[derive(Deserialize, Debug)]
struct SearchResult {
    title: String,
    link: String,
    snippet: String,
}

pub fn search_with_engine(engine: &str, query: &str) -> Vec<SearchResult> {
    let client = Client::new();
    let url = match engine {
        "google" => format!("https://www.google.com/search?q={}", query),
        "bing" => format!("https://www.bing.com/search?q={}", query),
        "yandex" => format!("https://yandex.com/search/?text={}", query),
        "duckduckgo" => format!("https://duckduckgo.com/?q={}", query),
        "yahoo" => format!("https://search.yahoo.com/search?p={}", query),
        "brave" => format!("https://search.brave.com/search?q={}", query),
        _ => return vec![],
    };

    let response = client.get(&url).send().unwrap().json::<Vec<SearchResult>>().unwrap();
    response
}
